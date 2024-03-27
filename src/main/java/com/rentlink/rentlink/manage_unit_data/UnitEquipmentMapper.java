package com.rentlink.rentlink.manage_unit_data;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
interface UnitEquipmentMapper {

    UnitEquipmentDTO map(UnitEquipment unitEquipment);

    UnitEquipment map(UnitEquipmentDTO unitEquipmentDTO);
}
