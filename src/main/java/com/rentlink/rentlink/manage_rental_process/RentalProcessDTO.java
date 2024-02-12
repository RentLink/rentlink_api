package com.rentlink.rentlink.manage_rental_process;

import java.util.List;
import java.util.UUID;

record RentalProcessDTO(
        UUID id,
        UUID rentalOptionId,
        String name,
        ProcessDefinitionType definitionType,
        List<ProcessStepDTO> steps,
        UUID currentProcessStepId) {}
