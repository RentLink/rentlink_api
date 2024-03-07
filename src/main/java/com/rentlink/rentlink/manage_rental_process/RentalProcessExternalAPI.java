package com.rentlink.rentlink.manage_rental_process;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import java.util.UUID;

interface RentalProcessExternalAPI {

    RentalProcessDTO createRentalProcess(UUID accountId, RentalProcessDTO rentalProcessDTO);

    List<RentalProcessDTO> getRentalProcessesForOption(UUID rentalOptionId, UUID accountId);

    RentalProcessDTO getRentalProcesses(UUID rentalProcessId, UUID accountId);

    RentalProcessDTO updateRentalProcess(UUID rentalProcessId, UUID accountId, RentalProcessDTO rentalProcessDTO)
            throws JsonProcessingException;

    RentalProcessDTO rejectRentalProcess(UUID rentalProcessId, UUID accountId);
}
