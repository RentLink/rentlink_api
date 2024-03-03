package com.rentlink.rentlink.manage_rental_process;

import java.util.List;
import java.util.UUID;

interface RentalProcessExternalAPI {

    RentalProcessDTO createRentalProcess(UUID accountId, RentalProcessDTO rentalProcessDTO);

    List<RentalProcessDTO> getRentalProcessesForOption(UUID rentalProcessId, UUID accountId);

    RentalProcessDTO updateRentalProcess(UUID rentalProcessId, UUID accountId, RentalProcessDTO rentalProcessDTO);

    RentalProcessDTO rejectRentalProcess(UUID rentalProcessId, UUID accountId);
}
