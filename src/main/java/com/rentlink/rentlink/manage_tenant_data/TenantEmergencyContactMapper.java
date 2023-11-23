package com.rentlink.rentlink.manage_tenant_data;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface TenantEmergencyContactMapper {
    TenantEmergencyContactDTO toDTO(TenantEmergencyContact tenantEmergencyContact);

    TenantEmergencyContact toDB(TenantEmergencyContactDTO tenantEmergencyContactDTO);
}
