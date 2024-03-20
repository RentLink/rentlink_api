package com.rentlink.rentlink.manage_email_outbound;

import java.util.List;
import java.util.UUID;

public record InternalEmailOrderDTO(
        UUID id, UUID accountId, String email, String subject, String message, List<String> files) {

    public static InternalEmailOrderDTO orderForSendingDocumentsInRentalProcess(
            UUID accountId, String email, List<String> files, UUID recognitionCode) {
        return new InternalEmailOrderDTO(
                null,
                accountId,
                email,
                "Dokumenty do procesu wynajmu",
                "Przesyłamy dokumentu potrzebne w procesie wynajmu mieszkania.\nW załączniku znajdziesz listę wymaganych dokumentów.\nProszę odesłać dokumenty na support@rentlink.com a w tytule podać kod %s\nW przypadku pytań proszę o kontakt.\n"
                        .formatted(recognitionCode.toString()),
                files);
    }
}
