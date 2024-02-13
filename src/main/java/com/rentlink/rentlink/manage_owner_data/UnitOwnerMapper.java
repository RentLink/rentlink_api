package com.rentlink.rentlink.manage_owner_data;

import org.mapstruct.AfterMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Set;

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
