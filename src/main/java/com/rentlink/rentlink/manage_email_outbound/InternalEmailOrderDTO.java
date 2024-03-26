package com.rentlink.rentlink.manage_email_outbound;

import java.util.List;
import java.util.UUID;

public record InternalEmailOrderDTO(
        UUID id, UUID accountId, String email, String subject, List<String> files, UUID recognitionCode) {

    public static InternalEmailOrderDTO orderForSendingDocumentsInRentalProcess(
            UUID accountId, String email, List<String> files, UUID recognitionCode) {
        return new InternalEmailOrderDTO(
                null,
                accountId,
                email,
                "Dokumenty do procesu wynajmu w procesie nr %s".formatted(recognitionCode),
                files,
                recognitionCode);
    }
}
