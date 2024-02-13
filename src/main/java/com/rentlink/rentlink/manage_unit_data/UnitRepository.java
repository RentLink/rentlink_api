package com.rentlink.rentlink.manage_unit_data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface UnitRepository extends JpaRepository<Unit, UUID> {}
