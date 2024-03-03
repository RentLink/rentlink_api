package com.rentlink.rentlink.manage_rental_options;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalOptionRepository extends JpaRepository<RentalOption, UUID> {
    Stream<RentalOption> findRentalOptionsByAccountIdAndUnitId(UUID accountId, UUID unitId);

    Optional<RentalOption> findByAccountIdAndId(UUID accountId, UUID id);
}
