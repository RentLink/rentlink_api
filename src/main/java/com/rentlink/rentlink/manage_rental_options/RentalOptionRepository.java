package com.rentlink.rentlink.manage_rental_options;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface RentalOptionRepository extends JpaRepository<RentalOption, UUID> {
    Set<RentalOption> findRentalOptionByUnitId(UUID unitId);
}
