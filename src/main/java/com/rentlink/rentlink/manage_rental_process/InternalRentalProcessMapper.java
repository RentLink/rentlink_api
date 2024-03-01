package com.rentlink.rentlink.manage_rental_process;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
interface InternalRentalProcessMapper {

    InternalRentalProcessDTO map(RentalProcess rentalProcess);
}
