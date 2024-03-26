package com.rentlink.rentlink.manage_email_inbound;

import java.util.Optional;
import java.util.UUID;

public interface AwaitingDocumentsInternalAPI {

    void acceptAwaitingDocumentTask(UUID accountId, UUID rentalProcessId, String email, UUID recognitionCode);

    Optional<AwaitingDocumentTaskDTO> markAsReceived(UUID accountId, UUID awaitingDocumentTaskId);

    Optional<AwaitingDocumentTaskDTO> getMatchingTask(String subject);
}
