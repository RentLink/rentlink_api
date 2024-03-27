package com.rentlink.rentlink.manage_unit_data;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
interface UtilityMapper {

    UtilityDTO map(Utility utility);

    Utility map(UtilityDTO utilityDTO);
}
