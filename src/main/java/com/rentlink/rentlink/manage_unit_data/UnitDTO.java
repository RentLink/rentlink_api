package com.rentlink.rentlink.manage_unit_data;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnitDTO {
    private UUID id;
    private String name;
    private UnitType unitType;
    private RentalType rentalType;
    private HeatingType heatingType;
    private Integer surface;
    private Short roomsNo;
    private String city;
    private String postalCode;
    private String street;
    private String buildingNumber;
    private String apartmentNumber;
    private String country;
    private String additionalInformation;
    private String insuranceNumber;
    private String insuranceCompany;
    private String insuranceDueDate;
    private Integer cooperativeFee;
    private Integer rentalFee;
}
