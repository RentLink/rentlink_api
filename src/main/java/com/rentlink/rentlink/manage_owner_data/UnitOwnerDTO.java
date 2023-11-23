package com.rentlink.rentlink.manage_owner_data;

import com.rentlink.rentlink.common.enums.Gender;
import com.rentlink.rentlink.common.enums.IdentityDocument;
import com.rentlink.rentlink.common.enums.LegalPersonality;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnitOwnerDTO {
    private UUID id;
    private LegalPersonality legalPersonality;
    private String name;
    private String surname;
    private Gender gender;
    private String citizenship;
    private String city;
    private String postalCode;
    private String street;
    private String buildingNumber;
    private String apartmentNumber;
    private String country;
    private IdentityDocument identityDocumentType;
    private String identityDocumentNumber;
    private String taxAccountNumber;
    private String bankAccountNumber;
    private String nip;
    private String regon;
    private String krs;
    private Set<UnitOwnerContactDetailsDTO> contactDetails;
    private Set<UnitOwnerEmergencyContactDTO> emergencyContacts;
}
