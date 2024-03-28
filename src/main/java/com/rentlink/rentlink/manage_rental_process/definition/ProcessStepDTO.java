package com.rentlink.rentlink.manage_rental_process.definition;

import java.util.List;
import java.util.UUID;

public record ProcessStepDTO(
        UUID stepId, String name, int order, ProcessStepType type, List<ProcessDataInputDTO<?>> inputs) {}
