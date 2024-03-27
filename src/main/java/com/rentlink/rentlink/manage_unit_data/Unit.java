package com.rentlink.rentlink.manage_unit_data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "unit", schema = "rentlink")
@Getter
@Setter
class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "account_id")
    private UUID accountId;

    // GENERAL

    private String name;

    @Column(name = "unit_type")
    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    @Column(name = "rental_type")
    @Enumerated(EnumType.STRING)
    private RentalType rentalType;

    private Integer surface;

    @Column(name = "rooms_no")
    private Short roomsNo;

    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    private String street;

    private String country;

    private String district;

    @Column(name = "cooperative_fee")
    private BigDecimal cooperativeFee;

    @Column(name = "rental_fee")
    private BigDecimal rentalFee;

    @Column(name = "building_no")
    private String buildingNumber;

    // APARTMENT, HOUSE

    @Column(name = "estate_name")
    private String estateName;

    @Column(name = "apartment_no")
    private String apartmentNumber;

    @Column(name = "additional_information")
    private String additionalInformation;

    @Column(name = "insurance_number")
    private String insuranceNumber;

    @Column(name = "insurance_company")
    private String insuranceCompany;

    @Column(name = "insurance_due_date")
    private LocalDate insuranceDueDate;

    private Integer floor;

    @Column(name = "total_floors")
    private Integer totalFloors;

    @Column(name = "doorbell_code")
    private String doorbellCode;

    @Column(name = "is_elevator_in_building")
    private Boolean isElevatorInBuilding;

    @Column(name = "development_type")
    @Enumerated(EnumType.STRING)
    private DevelopmentType developmentType;

    @Column(name = "windows_type")
    @Enumerated(EnumType.STRING)
    private WindowsType windowsType;

    @Column(name = "heating_type")
    @Enumerated(EnumType.STRING)
    private HeatingType heatingType;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "unit")
    private List<UnitEquipment> unitEquipment;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "unit")
    private List<AssociatedRoom> associatedRoom;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "unit")
    private List<Utility> utilities;

    // GARAGE, PARKING

    @Column(name = "garage_number")
    private String garageNumber;

    @Column(name = "garage_level")
    private String garageLevel;

    @Column(name = "garage_entrance_type")
    @Enumerated(EnumType.STRING)
    private GarageEntranceType garageEntranceType;

    @Column(name = "garage_entrance_code")
    private String garageEntranceCode;

    @Column(name = "garage_type")
    private GarageType garageType;
}
