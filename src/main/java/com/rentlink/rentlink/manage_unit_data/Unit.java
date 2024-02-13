package com.rentlink.rentlink.manage_unit_data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "unit", schema = "rentlink")
@Getter
@Setter
class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(name = "unit_type")
    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    @Column(name = "rental_type")
    @Enumerated(EnumType.STRING)
    private RentalType rentalType;

    @Column(name = "heating_type")
    @Enumerated(EnumType.STRING)
    private HeatingType heatingType;

    @Column(name = "rental_option_type")
    @Enumerated(EnumType.STRING)
    private RentalOptionType rentalOptionType;

    private Integer surface;

    @Column(name = "rooms_no")
    private Short roomsNo;

    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    private String street;

    @Column(name = "building_no")
    private String buildingNumber;

    @Column(name = "apartment_no")
    private String apartmentNumber;

    private String country;

    @Column(name = "additional_information")
    private String additionalInformation;

    @Column(name = "insurance_number")
    private String insuranceNumber;

    @Column(name = "insurance_company")
    private String insuranceCompany;

    @Column(name = "insurance_due_date")
    private LocalDate insuranceDueDate;

    @Column(name = "cooperative_fee")
    private BigDecimal cooperativeFee;

    @Column(name = "rental_fee")
    private BigDecimal rentalFee;
}
