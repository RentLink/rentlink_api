package com.rentlink.rentlink.manage_rental_process;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

interface RentalProcessRepository extends JpaRepository<RentalProcess, UUID> {

    Stream<RentalProcess> findAllByRentalOptionIdAndAccountId(UUID rentalOptionId, UUID accountId);

    Optional<RentalProcess> findByAccountIdAndId(UUID accountId, UUID id);

    List<RentalProcess> findAllByUpdatedAtBefore(Instant date);

    @Modifying
    @Query(
            nativeQuery = true,
            value =
                    "UPDATE rentlink.rental_process SET definition = CAST(:definition AS jsonb), updated_at = now(), current_step_id = CAST(:currentStepId as uuid) , status = :status   WHERE id = :id")
    void updateDefinition(
            @Param("id") UUID id,
            @Param("definition") String definition,
            @Param("currentStepId") String currentStepId,
            @Param("status") String status);
}
