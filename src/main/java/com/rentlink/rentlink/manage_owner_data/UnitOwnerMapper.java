package com.rentlink.rentlink.manage_owner_data;

import java.util.Set;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {UnitOwnerContactDetailsMapper.class, UnitOwnerEmergencyContactMapper.class})
interface UnitOwnerMapper {
    UnitOwnerDTO toDTO(UnitOwner unitOwner);

    UnitOwner toDB(UnitOwnerDTO owner);

    @AfterMapping
    default void setUnitOwner(@MappingTarget UnitOwner unitOwner) {

        Set<UnitOwnerContactDetails> unitOwnerContactDetails = unitOwner.getContactDetails();
        if (unitOwnerContactDetails != null) {
            unitOwnerContactDetails.forEach(cd -> cd.setUnitOwner(unitOwner));
        }

        Set<UnitOwnerEmergencyContact> unitOwnerEmergencyContacts = unitOwner.getEmergencyContacts();
        if (unitOwnerEmergencyContacts != null) {
            unitOwnerEmergencyContacts.forEach(ec -> ec.setUnitOwner(unitOwner));
        }
    }
}
