package com.rentlink.rentlink.manage_owner_data;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface UnitOwnerContactDetailsMapper {

    UnitOwnerContactDetailsDTO toDTO(UnitOwnerContactDetails unitOwnerContactDetails);
    UnitOwnerContactDetails toDB(UnitOwnerContactDetailsDTO unitOwnerContactDetailsDTO);
}
