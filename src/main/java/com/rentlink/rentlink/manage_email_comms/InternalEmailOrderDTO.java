package com.rentlink.rentlink.manage_email_comms;

import java.util.List;
import java.util.UUID;

public record InternalEmailOrderDTO(UUID accountId, String email, String subject, String message, List<String> files) {

    public static InternalEmailOrderDTO orderForSendingDocumentsInRentalProcess(UUID accountId, String email, List<String> files) {
        return new InternalEmailOrderDTO(
                accountId,
                email,
                "Documents for rental process",
                "Please find attached the documents for the rental process",
                files);
    }
}
