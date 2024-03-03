package com.rentlink.rentlink.manage_tenant_data;

import java.util.Set;
import java.util.UUID;

interface TenantExternalAPI {

    TenantDTO getTenant(UUID tenantId, UUID accountId);

    Set<TenantDTO> getTenants(UUID accountId);

    Set<TenantDTO> quickSearch(UUID accountId, String value);

    Set<TenantDTO> search(Integer page, Integer pageSize, UUID accountId, SearchTenant searchTenant);

    TenantDTO addTenant(TenantDTO tenantDTO, UUID accountId);

    TenantDTO patchTenant(UUID id, UUID accountId, TenantDTO tenantDTO);

    void deleteTenant(UUID tenantId, UUID accountId);
}
