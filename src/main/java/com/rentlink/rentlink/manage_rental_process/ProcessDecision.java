package com.rentlink.rentlink.manage_rental_process;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
enum ProcessDecision {
    CONTRACT("UMOWA"),
    REJECTION("ODRZUCENIE NAJEMCY");

    private final String name;
}
