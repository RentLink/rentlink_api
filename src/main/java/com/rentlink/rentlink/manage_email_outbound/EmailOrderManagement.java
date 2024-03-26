package com.rentlink.rentlink.manage_email_outbound;

import com.rentlink.rentlink.manage_files.FileDTO;
import com.rentlink.rentlink.manage_files.FilesManagerInternalAPI;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
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
    public void acceptEmailSendOrder(UUID accountId, InternalEmailOrderDTO emailOrderDTO) {
        log.info(
                "Received email order to send to {} with attachments {}", emailOrderDTO.email(), emailOrderDTO.files());
        var files =
                filesManagerInternalAPI.getFiles(accountId.toString(), new HashSet<>(emailOrderDTO.files())).stream()
                        .map(FileDTO::file)
                        .collect(Collectors.toList());
        // TODO: add retry logic
        var emailSendTrial = emailSender.executeEmailSend(emailOrderDTO, files);
        var emailOrder = emailOrderMapper.map(emailOrderDTO);
        if (emailSendTrial.isSuccess()) {
            log.info(
                    "Email order sent successfully to {} with attachments {}",
                    emailOrderDTO.email(),
                    emailOrderDTO.files());
            emailOrder.setStatus(EmailOrderStatus.SENT);
            emailOrder.setSentAt(LocalDateTime.now());
        } else {
            log.error(
                    "Failed to send email order, saving it to database, we will retry later",
                    emailSendTrial.getCause());
            emailOrder.setStatus(EmailOrderStatus.FAILED);
            emailOrder.setErrorMessage(emailSendTrial.getCause().getMessage());
        }
        // TODO: add fatal handling
        emailOrderRepository.save(emailOrder);
    }

    @Transactional(readOnly = true)
    @Override
    public List<InternalEmailOrderDTO> getFailedEmails() {
        return emailOrderRepository
                .findAllByStatus(EmailOrderStatus.FAILED)
                .map(emailOrderMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void resendFailedEmail(InternalEmailOrderDTO internalEmailOrderDTO) {
        EmailOrder emailOrder = emailOrderRepository
                .findByAccountIdAndId(internalEmailOrderDTO.accountId(), internalEmailOrderDTO.id())
                .orElseThrow(RuntimeException::new);
        InternalEmailOrderDTO emailOrderDTO = emailOrderMapper.map(emailOrder);
        var files =
                filesManagerInternalAPI
                        .getFiles(emailOrder.getAccountId().toString(), new HashSet<>(emailOrderDTO.files()))
                        .stream()
                        .map(FileDTO::file)
                        .collect(Collectors.toList());
        var emailSendTrial = emailSender.executeEmailSend(emailOrderDTO, files);
        if (emailSendTrial.isSuccess()) {
            emailOrder.setStatus(EmailOrderStatus.SENT);
            emailOrder.setSentAt(LocalDateTime.now());
            emailOrder.setErrorMessage(null);
            emailOrderRepository.save(emailOrder);
        } else {
            log.error(
                    "Failed to send email order, updating error in database, we will retry later",
                    emailSendTrial.getCause());
            emailOrder.setErrorMessage(emailSendTrial.getCause().getMessage());
            emailOrderRepository.save(emailOrder);
        }
    }
}
