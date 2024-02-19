package com.rentlink.rentlink.manage_unit_data;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

interface UnitRepository extends JpaRepository<Unit, UUID> {}
