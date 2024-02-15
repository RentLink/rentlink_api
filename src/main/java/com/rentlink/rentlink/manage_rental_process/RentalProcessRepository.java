package com.rentlink.rentlink.manage_rental_process;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

interface RentalProcessRepository extends JpaRepository<RentalProcess, UUID> {

    List<RentalProcess> findAllByRentalOptionId(UUID rentalOptionId);

    List<RentalProcess> findAllByUpdatedAtBefore(Instant date);
}
