package com.rentlink.rentlink.manage_tenant_data;

import com.rentlink.rentlink.common.enums.Gender;
import com.rentlink.rentlink.common.enums.IdentityDocument;
import com.rentlink.rentlink.common.enums.LegalPersonality;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

record TenantDTO(
        UUID id,
        LegalPersonality legalPersonality,
        String name,
        String surname,
        Gender gender,
        String citizenship,
        String city,
        String postalCode,
        String street,
        String buildingNumber,
        String apartmentNumber,
        String country,
        IdentityDocument identityDocumentType,
        String identityDocumentNumber,
        LocalDate identityDocumentIssueDate,
        LocalDate identityDocumentDueDate,
        String bankAccountNumber,
        String bankName,
        String companyName,
        String companyCity,
        String companyPostalCode,
        String companyStreet,
        String companyBuildingNumber,
        String companyApartmentNumber,
        String companyCountry,
        String socialNumber,
        String nip,
        String regon,
        String krs,
        String phoneNumber,
        String email,
        Set<TenantEmergencyContactDTO> emergencyContacts) {}
