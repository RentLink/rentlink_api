package com.rentlink.rentlink.manage_rental_process;

import java.util.List;
import java.util.UUID;

record ProcessDefinition(
        UUID id,
        String name,
        ProcessDefinitionType definitionType,
        List<ProcessStep> steps,
        UUID currentProcessStepId) {}
