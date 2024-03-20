package com.rentlink.rentlink.manage_email_inbound;

import com.rentlink.rentlink.manage_files.FilesManagerInternalAPI;
import com.rentlink.rentlink.manage_files.MimebodyPartToSave;
import com.rentlink.rentlink.manage_notifications.NotificationDTO;
import com.rentlink.rentlink.manage_notifications.NotificationsWebSocketHandler;
import com.rentlink.rentlink.manage_notifications.Priority;
import com.sun.mail.imap.IMAPFolder;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Part;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.event.MessageCountAdapter;
import jakarta.mail.event.MessageCountEvent;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailListener extends MessageCountAdapter {

    private static final String IMAPS = "imaps";
    private static final String INBOX = "INBOX";
    private final FilesManagerInternalAPI filesManagerInternalAPI;
    private final AwaitingDocumentsInternalAPI awaitingDocumentsInternalAPI;
    private final NotificationsWebSocketHandler notificationsWebSocketHandler;
    private final ExecutorService keepAliveExecutor = Executors.newSingleThreadExecutor();
    private final ExecutorService emailListenerExecutor = Executors.newSingleThreadExecutor();

    @Autowired
    public EmailListener(
            @Value("${rentlink.email.username}") String username,
            @Value("${rentlink.email.password}") String password,
            @Value("${rentlink.email.host}") String emailHost,
            @Value("${rentlink.email.port}") String emailPort,
            FilesManagerInternalAPI filesManagerInternalAPI,
            AwaitingDocumentsInternalAPI awaitingDocumentsInternalAPI,
            NotificationsWebSocketHandler notificationsWebSocketHandler)
            throws MessagingException {

        this.filesManagerInternalAPI = filesManagerInternalAPI;
        this.awaitingDocumentsInternalAPI = awaitingDocumentsInternalAPI;
        this.notificationsWebSocketHandler = notificationsWebSocketHandler;
        init(username, password, emailHost, emailPort);
    }

    private void init(String username, String password, String emailHost, String emailPort) throws MessagingException {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", IMAPS);
        props.setProperty("mail.imaps.host", emailHost);
        props.setProperty("mail.imaps.port", emailPort);
        Session session = Session.getInstance(props);
        Store store = session.getStore(IMAPS);
        store.connect(username, password);

        IMAPFolder inbox = (IMAPFolder) store.getFolder(INBOX);
        inbox.open(Folder.READ_WRITE);

        inbox.addMessageCountListener(new MessageCountAdapter() {
            @Override
            public void messagesAdded(MessageCountEvent event) {
                // Process the newly added messages
                Message[] messages = event.getMessages();
                for (Message message : messages) {
                    try {
                        Optional<AwaitingDocumentTaskDTO> matchingTask =
                                awaitingDocumentsInternalAPI.getMatchingTask(message.getSubject());
                        String subdirectory;
                        InternetAddress[] froms = (InternetAddress[]) message.getFrom();
                        String email = froms == null ? null : froms[0].getAddress();
                        if (matchingTask.isEmpty()) {
                            log.error("No matching task found for subject: {}", message.getSubject());
                            subdirectory = "documents-without-awaiting-process/" + email;
                            log.error("Not matching files will be saved in: {}", subdirectory);
                        } else {
                            log.info("Matching task found for subject: {}", message.getSubject());
                            subdirectory = matchingTask.get().accountId().toString() + "/rental-process/"
                                    + matchingTask.get().rentalProcessId().toString();
                        }
                        Multipart multiPart = (Multipart) message.getContent();
                        final Set<MimebodyPartToSave> files = Sets.newHashSet();
                        for (int i = 0; i < multiPart.getCount(); i++) {
                            MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(i);
                            if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                                files.add(new MimebodyPartToSave(subdirectory, part));
                            }
                        }
                        filesManagerInternalAPI.saveMimeFiles(files);
                        matchingTask.ifPresent(task -> {
                            awaitingDocumentsInternalAPI.markAsReceived(task.accountId(), task.id());
                            notificationsWebSocketHandler.sendNotification(
                                    task.accountId(),
                                    NotificationDTO.createNewNotification(
                                            "New documents received",
                                            "Rental process files received from %s".formatted(email),
                                            Priority.HIGH));
                        });
                    } catch (IOException | MessagingException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        keepAliveExecutor.submit(new KeepAliveTask(inbox));
        emailListenerExecutor.submit(() -> {
            // Start the IDLE Loop
            while (!Thread.interrupted()) {
                try {
                    log.info("Starting IDLE");
                    inbox.idle();
                } catch (MessagingException e) {
                    log.error("Unexpected exception while IDLE", e);
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
