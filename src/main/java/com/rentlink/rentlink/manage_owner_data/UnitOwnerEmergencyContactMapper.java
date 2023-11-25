package com.rentlink.rentlink.manage_owner_data;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface UnitOwnerEmergencyContactMapper {

    UnitOwnerEmergencyContactDTO map(UnitOwnerEmergencyContact unitOwnerEmergencyContact);

    @Mapping(target = "unitOwner", ignore = true)
    @Mapping(target = "id", ignore = true)
    UnitOwnerEmergencyContact map(UnitOwnerEmergencyContactDTO unitOwnerEmergencyContactDTO);
}
