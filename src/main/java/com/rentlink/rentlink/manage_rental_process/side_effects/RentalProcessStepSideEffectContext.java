package com.rentlink.rentlink.manage_rental_process.side_effects;

import com.rentlink.rentlink.manage_rental_process.definition.ProcessStepDTO;
import java.util.List;
import java.util.UUID;

public record RentalProcessStepSideEffectContext(
        UUID accountId, UUID rentalProcessId, List<ProcessStepDTO> steps, UUID recognitionCode) {}
