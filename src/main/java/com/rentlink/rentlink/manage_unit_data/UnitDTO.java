package com.rentlink.rentlink.manage_unit_data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

record UnitDTO(
        UUID id,
        String name,
        UnitType unitType,
        RentalType rentalType,
        HeatingType heatingType,
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
        BigDecimal rentalFee) {}
