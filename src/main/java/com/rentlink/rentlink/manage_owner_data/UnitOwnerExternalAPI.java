package com.rentlink.rentlink.manage_owner_data;

import java.util.Set;
import java.util.UUID;

interface UnitOwnerExternalAPI {

    UnitOwnerDTO getUnitOwner(UUID ownerId, UUID accountId);

    Set<UnitOwnerDTO> getUnitOwners(UUID accountId, Integer page, Integer pageSize);

    UnitOwnerDTO addUnitOwner(UUID accountId, UnitOwnerDTO unitOwnerDTO);

    UnitOwnerDTO patchUnitOwner(UUID id, UUID accountId, UnitOwnerDTO unitOwnerDTO);

    void deleteUnitOwner(UUID ownerId, UUID accountId);
}
