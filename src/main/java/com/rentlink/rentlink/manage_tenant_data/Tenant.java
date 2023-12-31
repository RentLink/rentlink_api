package com.rentlink.rentlink.manage_tenant_data;

import com.rentlink.rentlink.common.enums.Gender;
import com.rentlink.rentlink.common.enums.IdentityDocument;
import com.rentlink.rentlink.common.enums.LegalPersonality;
import jakarta.persistence.*;
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

    private String nip;

    private String regon;

    private String krs;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "tenant")
    private Set<TenantEmergencyContact> emergencyContacts;
}
