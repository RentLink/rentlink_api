package com.rentlink.rentlink.manage_tenant_data;

import java.util.Set;
import org.mapstruct.AfterMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {TenantEmergencyContactMapper.class})
interface TenantMapper {
    TenantDTO map(Tenant tenant);

    @Mapping(target = "id", ignore = true)
    Tenant map(TenantDTO tenantDTO);

    @InheritConfiguration
    void update(TenantDTO tenantDTO, @MappingTarget Tenant tenant);

    @AfterMapping
    default void setUnitOwner(@MappingTarget Tenant tenant) {
        Set<TenantEmergencyContact> unitOwnerEmergencyContacts = tenant.getEmergencyContacts();
        if (unitOwnerEmergencyContacts != null) {
            unitOwnerEmergencyContacts.forEach(ec -> ec.setTenant(tenant));
        }
    }
}
