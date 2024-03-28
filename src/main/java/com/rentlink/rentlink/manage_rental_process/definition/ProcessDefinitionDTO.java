package com.rentlink.rentlink.manage_rental_process.definition;

import java.util.List;
import java.util.UUID;

public record ProcessDefinitionDTO(
        UUID id, String name, ProcessDefinitionType definitionType, List<ProcessStepDTO> steps) {}
