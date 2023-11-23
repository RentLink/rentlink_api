package com.rentlink.rentlink.manage_tenant_data;

import java.util.Set;
import java.util.UUID;

public interface TenantExternalAPI {

    TenantDTO getTenant(UUID tenantId);

    Set<TenantDTO> getTenants();

    TenantDTO addTenant(TenantDTO tenantDTO);

    TenantDTO updateTenant(TenantDTO tenantDTO);

    void deleteTenant(UUID tenantId);
}
