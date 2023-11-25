package com.rentlink.rentlink.manage_unit_data;

import java.util.Set;
import java.util.UUID;

interface UnitExternalAPI {
    UnitDTO getUnit(UUID unitId);

    Set<UnitDTO> getUnits();

    UnitDTO addUnit(UnitDTO unitDTO);

    UnitDTO updateUnit(UnitDTO unitDTO);

    void deleteUnit(UUID unitId);
}
