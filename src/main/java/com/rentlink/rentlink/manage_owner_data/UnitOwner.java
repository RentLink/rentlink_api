package com.rentlink.rentlink.manage_owner_data;

import com.rentlink.rentlink.common.enums.IdentityDocument;
import com.rentlink.rentlink.common.enums.Gender;
import com.rentlink.rentlink.common.enums.LegalPersonality;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "unit_owner", schema = "rentlink")
@Getter
@Setter
class UnitOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "legal_personality")
    private LegalPersonality legalPersonality;

    private String name;

    private String surname;

    private Gender gender;

    private String citizenship;

    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    private String street;

    @Column(name = "building_no")
    private String buildingNumber;

    @Column(name = "apartment_no")
    private String apartmentNumber;

    private String country;

    @Column(name = "identity_document_type")
    private IdentityDocument identityDocumentType;

    @Column(name = "identity_document_number")
    private String identityDocumentNumber;

    @Column(name = "tax_account_number")
    private String taxAccountNumber;

    @Column(name = "bank_account_number")
    private String bankAccountNumber;

    private String nip;

    private String regon;

    private String krs;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy="unitOwner")
    private Set<UnitOwnerContactDetails> contactDetails;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy="unitOwner")
    private Set<UnitOwnerEmergencyContact> emergencyContacts;

}
