package com.rentlink.rentlink.manage_email_comms;

import com.rentlink.rentlink.manage_files.FileDTO;
import com.rentlink.rentlink.manage_files.FileName;
import com.rentlink.rentlink.manage_files.FilesManagerInternalAPI;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduledJob {

    private final EmailOrderRepository emailOrderRepository;

    private final EmailSender emailSender;

    private final EmailOrderMapper emailOrderMapper;

    private final FilesManagerInternalAPI filesManagerInternalAPI;

    @Scheduled(cron = "0 */15 * ? * *")
    public void sendEmails() {
        emailOrderRepository.findAll().forEach(emailOrder -> {
            EmailOrderDTO emailOrderDTO = emailOrderMapper.map(emailOrder);
            var files = filesManagerInternalAPI
                    .getFiles(
                            "",
                            emailOrderDTO.files().stream().map(FileName::new).collect(Collectors.toSet()))
                    .stream()
                    .map(FileDTO::file)
                    .collect(Collectors.toList());
            var emailSendTrial = emailSender.executeEmailSend(emailOrderDTO, files);
            if (emailSendTrial.isSuccess()) {
                emailOrderRepository.delete(emailOrder);
            }
        });
    }
}
