package com.rentlink.rentlink.manage_owner_data;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface UnitOwnerRepository extends JpaRepository<UnitOwner, UUID> {

    Optional<UnitOwner> findByAccountIdAndId(UUID accountId, UUID ownerId);

    void deleteByAccountIdAndId(UUID accountId, UUID ownerId);

    Stream<UnitOwner> findAllByAccountId(UUID accountId);

    Stream<UnitOwner> findAllByAccountId(UUID accountId, Pageable pageable);
}
