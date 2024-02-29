package com.rentlink.rentlink.manage_email_comms;

import com.rentlink.rentlink.manage_files.FileDTO;
import com.rentlink.rentlink.manage_files.FileName;
import com.rentlink.rentlink.manage_files.FilesManagerInternalAPI;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
class EmailOrderManagement implements EmailOrderInternalAPI {

    private final EmailSender emailSender;

    private final FilesManagerInternalAPI filesManagerInternalAPI;

    private final EmailOrderRepository emailOrderRepository;

    private final EmailOrderMapper emailOrderMapper;

    @Override
    @Transactional
    @Async
    public void acceptEmailSendOrder(EmailOrderDTO emailOrderDTO) {
        var files =
                filesManagerInternalAPI
                        .getFiles(
                                "",
                                emailOrderDTO.files().stream()
                                        .map(FileName::new)
                                        .collect(Collectors.toSet()))
                        .stream()
                        .map(FileDTO::file)
                        .collect(Collectors.toList());
        // TODO: add retry logic
        var emailSendTrial = emailSender.executeEmailSend(emailOrderDTO, files);
        if (emailSendTrial.isFailure()) {
            log.info("Failed to send email order, saving it to database, we will retry later");

            // TODO: add fatal handling
            emailOrderRepository.save(emailOrderMapper.map(emailOrderDTO));
        }
    }
}
