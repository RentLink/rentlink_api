package com.rentlink.rentlink.manage_tenant_data;

import com.rentlink.rentlink.manage_owner_data.*;
import java.util.Set;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {TenantContactDetailsMapper.class, TenantEmergencyContactMapper.class})
interface TenantMapper {
    TenantDTO toDTO(Tenant tenant);

    Tenant toDB(TenantDTO tenantDTO);

    @AfterMapping
    default void setUnitOwner(@MappingTarget Tenant tenant) {

        Set<TenantContactDetails> unitOwnerContactDetails = tenant.getContactDetails();
        if (unitOwnerContactDetails != null) {
            unitOwnerContactDetails.forEach(cd -> cd.setTenant(tenant));
        }

        Set<TenantEmergencyContact> unitOwnerEmergencyContacts = tenant.getEmergencyContacts();
        if (unitOwnerEmergencyContacts != null) {
            unitOwnerEmergencyContacts.forEach(ec -> ec.setTenant(tenant));
        }
    }
}
