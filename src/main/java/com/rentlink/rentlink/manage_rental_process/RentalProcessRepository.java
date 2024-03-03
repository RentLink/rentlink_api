package com.rentlink.rentlink.manage_rental_process;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;

interface RentalProcessRepository extends JpaRepository<RentalProcess, UUID> {

    Stream<RentalProcess> findAllByRentalOptionIdAndAccountId(UUID rentalOptionId, UUID accountId);

    Optional<RentalProcess> findByAccountIdAndId(UUID accountId, UUID id);

    List<RentalProcess> findAllByUpdatedAtBefore(Instant date);
}
