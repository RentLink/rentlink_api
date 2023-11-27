package com.rentlink.rentlink.manage_owner_data;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

interface UnitOwnerRepository extends JpaRepository<UnitOwner, UUID> {}
