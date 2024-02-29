package com.rentlink.rentlink.manage_rental_process;

import io.soabase.recordbuilder.core.RecordBuilder;
import java.util.UUID;

@RecordBuilder
record RentalProcessDTO(
        UUID id,
        UUID rentalOptionId,
        RentalProcessStatus status,
        ProcessDefinitionDTO definition,
        UUID previousStepId,
        UUID currentStepId)
        implements RentalProcessDTOBuilder.With {}
