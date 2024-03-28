package com.rentlink.rentlink.manage_rental_process.definition;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
enum VerificationOutcome {
    POSITIVE("POZYTYWNA"),
    NEGATIVE("NEGATYWNA");

    private final String name;
}
