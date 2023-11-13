package com.rentlink.rentlink.manage_owner_data;

import java.util.Set;
import java.util.UUID;

public interface UnitOwnerExternalAPI {

    UnitOwnerDTO getUnitOwner(UUID ownerId);
    Set<UnitOwnerDTO> getUnitOwners();
    UnitOwnerDTO addUnitOwner(UnitOwnerDTO unitOwnerDTO);
    UnitOwnerDTO updateUnitOwner(UnitOwnerDTO unitOwnerDTO);
    void deleteUnitOwner(UUID ownerId);

}
