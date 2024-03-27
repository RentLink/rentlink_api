package com.rentlink.rentlink.manage_unit_data;

import com.rentlink.rentlink.manage_rental_options.RentalOptionDTO;
import io.soabase.recordbuilder.core.RecordBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RecordBuilder
record UnitDTO(
        UUID id,
        UUID accountId,

        // GENERAL
        String name,
        UnitType unitType,
        RentalType rentalType,
        Integer surface,
        Short roomsNo,
        String city,
        String postalCode,
        String street,
        String country,
        String district,
        BigDecimal cooperativeFee,
        BigDecimal rentalFee,
        String buildingNumber,
        Integer totalArea,

        // APARTMENT, HOUSE

        String estateName,
        String apartmentNumber,
        String additionalInformation,
        String insuranceNumber,
        String insuranceCompany,
        LocalDate insuranceDueDate,
        Integer floor,
        Integer totalFloors,
        String doorbellCode,
        Boolean isElevatorInBuilding,
        DevelopmentType developmentType,
        WindowsType windowsType,
        HeatingType heatingType,
        List<UnitEquipmentDTO> unitEquipment,
        List<AssociatedRoomDTO> associatedRoom,
        List<UtilityDTO> utilities,

        // GARAGE, PARKING

        String garageNumber,
        String garageLevel,
        GarageEntranceType garageEntranceType,
        String garageEntranceCode,
        GarageType garageType,
        Set<RentalOptionDTO> rentalOptions)
        implements UnitDTOBuilder.With {}
