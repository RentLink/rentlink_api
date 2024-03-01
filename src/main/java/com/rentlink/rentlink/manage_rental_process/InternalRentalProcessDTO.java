package com.rentlink.rentlink.manage_rental_process;

import java.util.UUID;

public record InternalRentalProcessDTO(
        UUID id,
        UUID accountId,
        UUID rentalOptionId,
        RentalProcessStatus status,
        ProcessDefinitionDTO definition,
        UUID previousStepId,
        UUID currentStepId) {}
