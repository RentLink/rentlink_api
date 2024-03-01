package com.rentlink.rentlink.manage_unit_data;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface UnitRepository extends JpaRepository<Unit, UUID> {

    Optional<Unit> findByAccountIdAndId(UUID accountId, UUID unitId);

    void deleteByAccountIdAndId(UUID accountId, UUID unitId);

    Stream<Unit> findAllByAccountId(UUID accountId, Pageable pageable);

    Stream<Unit> findBtAccountId(UUID accountId);
}
