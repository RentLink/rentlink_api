package com.rentlink.rentlink.manage_unit_data;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

interface UnitRepository extends CrudRepository<Unit, UUID> {}
