package com.rentlink.rentlink.manage_tenant_data;

import java.util.Set;
import java.util.UUID;

interface TenantExternalAPI {

    TenantDTO getTenant(UUID tenantId);

    Set<TenantDTO> getTenants();

    Set<TenantDTO> searchTenants(Integer page, Integer pageSize, SearchTenant searchTenant);

    TenantDTO addTenant(TenantDTO tenantDTO);

    TenantDTO patchTenant(UUID id, TenantDTO tenantDTO);

    void deleteTenant(UUID tenantId);
}
