package com.rentlink.rentlink.manage_owner_data;

import java.util.Set;
import org.mapstruct.*;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {UnitOwnerEmergencyContactMapper.class})
interface UnitOwnerMapper {

    UnitOwnerDTO map(UnitOwner unitOwner);

    @Mapping(target = "id", ignore = true)
    UnitOwner map(UnitOwnerDTO owner);

    @InheritConfiguration
    void update(UnitOwnerDTO ownerDTO, @MappingTarget UnitOwner unitOwner);

    @AfterMapping
    default void setUnitOwner(@MappingTarget UnitOwner unitOwner) {
        Set<UnitOwnerEmergencyContact> unitOwnerEmergencyContacts = unitOwner.getEmergencyContacts();
        if (unitOwnerEmergencyContacts != null) {
            unitOwnerEmergencyContacts.forEach(ec -> ec.setUnitOwner(unitOwner));
        }
    }
}
