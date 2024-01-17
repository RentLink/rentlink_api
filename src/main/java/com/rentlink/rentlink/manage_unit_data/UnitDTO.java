package com.rentlink.rentlink.manage_unit_data;

import com.rentlink.rentlink.manage_rental_options.RentalOptionDTO;
import io.soabase.recordbuilder.core.RecordBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@RecordBuilder
record UnitDTO(
        UUID id,
        String name,
        UnitType unitType,
        RentalType rentalType,
        HeatingType heatingType,
        RentalOptionType rentalOptionType,
        Integer surface,
        Short roomsNo,
        String city,
        String postalCode,
        String street,
        String buildingNumber,
        String apartmentNumber,
        String country,
        String additionalInformation,
        String insuranceNumber,
        String insuranceCompany,
        LocalDate insuranceDueDate,
        BigDecimal cooperativeFee,
        BigDecimal rentalFee,
        Set<RentalOptionDTO> rentalOptions)
        implements UnitDTOBuilder.With {}
