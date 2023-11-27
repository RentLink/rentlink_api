package com.rentlink.rentlink.manage_owner_data;

import java.util.Set;
import java.util.UUID;

interface UnitOwnerExternalAPI {

    UnitOwnerDTO getUnitOwner(UUID ownerId);

    Set<UnitOwnerDTO> getUnitOwners(Integer page, Integer pageSize);

    UnitOwnerDTO addUnitOwner(UnitOwnerDTO unitOwnerDTO);

    UnitOwnerDTO patchUnitOwner(UUID id, UnitOwnerDTO unitOwnerDTO);

    void deleteUnitOwner(UUID ownerId);
}
