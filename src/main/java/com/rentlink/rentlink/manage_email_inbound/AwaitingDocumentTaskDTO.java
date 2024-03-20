package com.rentlink.rentlink.manage_email_inbound;

import java.util.UUID;

public record AwaitingDocumentTaskDTO(UUID accountId, UUID id, UUID rentalProcessId) {}
