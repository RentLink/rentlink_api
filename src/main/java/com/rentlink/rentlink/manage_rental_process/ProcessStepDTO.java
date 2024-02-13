package com.rentlink.rentlink.manage_rental_process;

import java.util.List;
import java.util.UUID;

record ProcessStepDTO(UUID stepId, String name, int order, ProcessStepType type, List<ProcessDataInputDTO<?>> inputs) {}
