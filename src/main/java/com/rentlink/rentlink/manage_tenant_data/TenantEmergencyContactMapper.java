package com.rentlink.rentlink.manage_tenant_data;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface TenantEmergencyContactMapper {
    TenantEmergencyContactDTO map(TenantEmergencyContact tenantEmergencyContact);

    @Mapping(target = "tenant", ignore = true)
    TenantEmergencyContact map(TenantEmergencyContactDTO tenantEmergencyContactDTO);
}
