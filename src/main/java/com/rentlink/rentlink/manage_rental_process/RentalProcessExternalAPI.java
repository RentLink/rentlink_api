package com.rentlink.rentlink.manage_rental_process;

import java.util.List;
import java.util.UUID;

interface RentalProcessExternalAPI {

    RentalProcessDTO createRentalProcess(RentalProcessDTO rentalProcessDTO);

    List<RentalProcessDTO> getRentalProcessesForOption(UUID rentalProcessId);

    RentalProcessDTO updateRentalProcess(UUID rentalProcessId, RentalProcessDTO rentalProcessDTO);

    RentalProcessDTO rejectRentalProcess(UUID rentalProcessId);
}
