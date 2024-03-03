package com.rentlink.rentlink.manage_email_outbound;

import java.util.List;

public record EmailOrderDTO(String email, String subject, String message, List<String> files) {

    public static EmailOrderDTO orderForSendingDocumentsInRentalProcess(String email, List<String> files) {
        return new EmailOrderDTO(
                email,
                "Documents for rental process",
                "Please find attached the documents for the rental process",
                files);
    }
}
