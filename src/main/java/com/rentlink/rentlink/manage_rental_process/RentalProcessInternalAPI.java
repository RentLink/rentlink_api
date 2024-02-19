package com.rentlink.rentlink.manage_rental_process;

import java.time.Instant;
import java.util.List;

public interface RentalProcessInternalAPI {

    List<RentalProcessDTO> findRentalProcessesUpdatedBefore(Instant instant);
}
