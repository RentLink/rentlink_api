package com.rentlink.rentlink.manage_rental_process;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface RentalProcessInternalAPI {

    List<InternalRentalProcessDTO> findRentalProcessesUpdatedBefore(Instant instant);

    void advanceProcessOnIncomingDocuments(UUID rentalProcessId, UUID accountId);
}
