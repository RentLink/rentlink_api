package com.rentlink.rentlink.manage_tenant_data;

import com.rentlink.rentlink.manage_owner_data.*;
import java.util.Set;
import org.mapstruct.*;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {TenantEmergencyContactMapper.class})
interface TenantMapper {
    TenantDTO toDTO(Tenant tenant);

    @Mapping(target = "id", ignore = true)
    Tenant toDB(TenantDTO tenantDTO);

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
