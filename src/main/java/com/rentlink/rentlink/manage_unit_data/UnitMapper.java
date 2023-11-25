package com.rentlink.rentlink.manage_unit_data;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface UnitMapper {

    UnitDTO toDTO(Unit unit);

    Unit toDB(UnitDTO unitDTO);
}
