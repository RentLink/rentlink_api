package com.rentlink.rentlink.manage_rental_process;

import com.rentlink.rentlink.manage_rental_process.definition.ProcessDefinitionDTO;
import com.rentlink.rentlink.manage_rental_process.definition.ProcessStepType;
import io.soabase.recordbuilder.core.RecordBuilder;
import java.util.UUID;

@RecordBuilder
record RentalProcessDTO(
        UUID id,
        UUID rentalOptionId,
        RentalProcessStatus status,
        ProcessDefinitionDTO definition,
        ProcessStepType currentStepType,
        UUID previousStepId,
        UUID currentStepId)
        implements RentalProcessDTOBuilder.With {}
