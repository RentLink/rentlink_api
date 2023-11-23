package com.rentlink.rentlink.manage_owner_data;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface UnitOwnerEmergencyContactMapper {
    UnitOwnerEmergencyContactDTO toDTO(UnitOwnerEmergencyContact unitOwnerEmergencyContact);

    UnitOwnerEmergencyContact toDB(UnitOwnerEmergencyContactDTO unitOwnerEmergencyContactDTO);
}
