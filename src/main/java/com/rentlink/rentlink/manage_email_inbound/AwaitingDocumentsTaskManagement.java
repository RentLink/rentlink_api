package com.rentlink.rentlink.manage_email_inbound;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AwaitingDocumentsTaskManagement implements AwaitingDocumentsInternalAPI {

    private final AwaitingDocumentTaskRepository awaitingDocumentTaskRepository;

    @Transactional
    @Override
    public void acceptAwaitingDocumentTask(UUID accountId, UUID rentalProcessId, String email, UUID recognitionCode) {
        awaitingDocumentTaskRepository.save(new AwaitingDocumentTask(
                accountId, rentalProcessId, email, recognitionCode, AwaitingTaskStatus.AWAITING));
    }

    @Transactional
    @Override
    public void markAsReceived(UUID accountId, UUID awaitingDocumentTaskId) {
        awaitingDocumentTaskRepository.findById(awaitingDocumentTaskId).ifPresent(task -> {
            task.setStatus(AwaitingTaskStatus.RECEIVED);
            awaitingDocumentTaskRepository.save(task);
        });
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<AwaitingDocumentTaskDTO> getMatchingTask(String subject) {
        List<AwaitingDocumentTask> tasks = awaitingDocumentTaskRepository
                .findByStatus(AwaitingTaskStatus.AWAITING)
                .filter(task -> subject.contains(task.getRecognitionCode().toString()))
                .toList();
        if (tasks.size() == 1) {
            AwaitingDocumentTask awaitingDocumentTask = tasks.get(0);
            return Optional.of(new AwaitingDocumentTaskDTO(
                    awaitingDocumentTask.getAccountId(),
                    awaitingDocumentTask.getId(),
                    awaitingDocumentTask.getRentalProcessId()));
        } else {
            log.error("No matching task found for subject: {}", subject);
        }
        return Optional.empty();
    }
}
