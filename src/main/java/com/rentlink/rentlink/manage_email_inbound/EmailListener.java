package com.rentlink.rentlink.manage_email_inbound;

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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jakarta.mail.internet.MimeBodyPart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailListener extends MessageCountAdapter {
    private final Session session;
    private String username;
    private String password;


    @Autowired
    public EmailListener(
            @Value("${rentlink.email.username}") String username,
            @Value("${rentlink.email.password}") String password,
            @Value("${rentlink.email.host}") String emailHost,
            @Value("${rentlink.email.port}") String emailPort)
            throws MessagingException {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        props.setProperty("mail.imaps.host", emailHost);
        props.setProperty("mail.imaps.port", emailPort);
        this.username = username;
        this.password = password;
        this.session = Session.getInstance(props);
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                startListening();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
    }

    public void startListening() throws MessagingException {
        Store store = session.getStore("imaps");
        store.connect(this.username, this.password);

        IMAPFolder inbox = (IMAPFolder) store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);

        // Create a new thread to keep the connection alive
        Thread keepAliveThread = new Thread(new KeepAliveRunnable(inbox), "IdleConnectionKeepAlive");
        keepAliveThread.start();

        inbox.addMessageCountListener(new MessageCountAdapter() {
            @Override
            public void messagesAdded(MessageCountEvent event) {
                // Process the newly added messages
                Message[] messages = event.getMessages();
                for (Message message : messages) {
                    try {
                        Multipart multiPart = (Multipart) message.getContent();
                        for (int i = 0; i < multiPart.getCount(); i++) {
                            MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(i);
                            if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {

                                String destFilePath = "/Users/szasiii/Projekty/rentlink/NEW/rentlink_api/local_files/" + part.getFileName();

                                FileOutputStream output = new FileOutputStream(destFilePath);

                                InputStream input = part.getInputStream();

                                byte[] buffer = new byte[4096];

                                int byteRead;

                                while ((byteRead = input.read(buffer)) != -1) {
                                    output.write(buffer, 0, byteRead);
                                }
                                output.close();
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }


                }
            }
        });

        // Start the IDLE Loop
        while (!Thread.interrupted()) {
            try {
                System.out.println("Starting IDLE");
                inbox.idle();
            } catch (MessagingException e) {
                System.out.println("Messaging exception during IDLE");
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        // Interrupt and shutdown the keep-alive thread
        if (keepAliveThread.isAlive()) {
            keepAliveThread.interrupt();
        }
    }
}
