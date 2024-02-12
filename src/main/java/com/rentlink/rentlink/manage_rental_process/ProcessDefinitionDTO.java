package com.rentlink.rentlink.manage_rental_process;

import java.util.List;
import java.util.UUID;

record ProcessDefinitionDTO(
        UUID id,
        String name,
        ProcessDefinitionType definitionType,
        List<ProcessStepDTO> steps,
        UUID currentProcessStepId) {}
