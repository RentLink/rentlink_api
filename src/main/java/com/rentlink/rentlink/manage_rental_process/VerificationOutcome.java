package com.rentlink.rentlink.manage_rental_process;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
enum VerificationOutcome {
    POSITIVE("POZYTYWNA"),
    NEGATIVE("NEGATYWNA");

    private final String name;
}
