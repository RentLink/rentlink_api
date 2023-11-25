package com.rentlink.rentlink.manage_owner_data;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

interface UnitOwnerRepository extends CrudRepository<UnitOwner, UUID> {}
