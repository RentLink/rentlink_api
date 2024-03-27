package com.rentlink.rentlink.manage_unit_data;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
interface AssociatedRoomMapper {

    AssociatedRoomDTO map(AssociatedRoom associatedRoom);

    AssociatedRoom map(AssociatedRoomDTO associatedRoomDTO);
}
