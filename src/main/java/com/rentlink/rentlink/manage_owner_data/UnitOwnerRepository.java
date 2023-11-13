package com.rentlink.rentlink.manage_owner_data;


import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

interface UnitOwnerRepository extends CrudRepository<UnitOwner, UUID> {
}
