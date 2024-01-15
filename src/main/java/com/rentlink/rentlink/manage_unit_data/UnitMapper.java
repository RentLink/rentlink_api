package com.rentlink.rentlink.manage_unit_data;

import org.mapstruct.*;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
interface UnitMapper {

    UnitDTO map(Unit unit);

    Unit map(UnitDTO unitDTO);

    @InheritConfiguration
    void update(UnitDTO unitDTO, @MappingTarget Unit unit);
}
