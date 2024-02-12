package com.rentlink.rentlink.manage_rental_options;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface RentalOptionMapper {

    RentalOptionDTO map(RentalOption rentalOption);

    RentalOption map(RentalOptionDTO rentalOptionDTO);

    @InheritConfiguration
    void update(RentalOptionDTO rentalOptionDTO, @MappingTarget RentalOption rentalOption);
}
