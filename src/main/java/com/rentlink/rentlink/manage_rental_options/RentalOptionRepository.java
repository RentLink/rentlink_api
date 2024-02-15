package com.rentlink.rentlink.manage_rental_options;

import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalOptionRepository extends JpaRepository<RentalOption, UUID> {
    Set<RentalOption> findRentalOptionByUnitId(UUID unitId);
}
