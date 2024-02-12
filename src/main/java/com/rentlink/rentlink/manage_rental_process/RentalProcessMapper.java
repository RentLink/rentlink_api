package com.rentlink.rentlink.manage_rental_process;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
interface RentalProcessMapper {

    RentalProcessDTO map(RentalProcess unit);

    RentalProcess map(RentalProcessDTO unitDTO);

    @InheritConfiguration
    void update(RentalProcessDTO unitDTO, @MappingTarget RentalProcess unit);
}
