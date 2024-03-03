package com.rentlink.rentlink.manage_unit_data;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
interface UnitMapper {

    UnitDTO map(Unit unit);

    @Mapping(target = "accountId", ignore = true)
    Unit map(UnitDTO unitDTO);

    @InheritConfiguration
    void update(UnitDTO unitDTO, @MappingTarget Unit unit);
}
