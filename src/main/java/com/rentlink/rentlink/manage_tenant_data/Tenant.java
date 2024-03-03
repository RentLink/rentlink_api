package com.rentlink.rentlink.manage_tenant_data;

import com.rentlink.rentlink.common.enums.Gender;
import com.rentlink.rentlink.common.enums.IdentityDocument;
import com.rentlink.rentlink.common.enums.LegalPersonality;
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
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tenant", schema = "rentlink")
@Getter
@Setter
class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "legal_personality")
    @Enumerated(EnumType.STRING)
    private LegalPersonality legalPersonality;

    private String name;

    private String surname;

    @Enumerated(EnumType.STRING)
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
    @Enumerated(EnumType.STRING)
    private IdentityDocument identityDocumentType;

    @Column(name = "identity_document_number")
    private String identityDocumentNumber;

    @Column(name = "identity_document_issue_date")
    private LocalDate identityDocumentIssueDate;

    @Column(name = "identity_document_due_date")
    private LocalDate identityDocumentDueDate;

    @Column(name = "bank_account_number")
    private String bankAccountNumber;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_city")
    private String companyCity;

    @Column(name = "company_postal_code")
    private String companyPostalCode;

    @Column(name = "company_street")
    private String companyStreet;

    @Column(name = "company_building_no")
    private String companyBuildingNumber;

    @Column(name = "company_apartment_no")
    private String companyApartmentNumber;

    @Column(name = "company_country")
    private String companyCountry;

    @Column(name = "social_number")
    private String socialNumber;

    private String nip;

    private String regon;

    private String krs;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "tenant")
    private Set<TenantEmergencyContact> emergencyContacts;
}
