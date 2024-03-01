package com.rentlink.rentlink.manage_tenant_data;

import static com.rentlink.rentlink.common.CustomHeaders.X_USER_HEADER;

import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tenant")
@RequiredArgsConstructor
class TenantEndpoint {

    private final TenantExternalAPI tenantExternalAPI;

    @GetMapping("/{tenantId}")
    public TenantDTO getTenant(@RequestHeader(value = X_USER_HEADER) UUID accountId, @PathVariable UUID tenantId) {
        return tenantExternalAPI.getTenant(tenantId, accountId);
    }

    @GetMapping("/")
    Set<TenantDTO> getTenants(@RequestHeader(value = X_USER_HEADER) UUID accountId) {
        return tenantExternalAPI.getTenants(accountId);
    }

    @GetMapping(value = "")
    Set<TenantDTO> searchTenants(
            @RequestHeader(value = X_USER_HEADER) UUID accountId, int page, int size, SearchTenant searchTenant) {
        return tenantExternalAPI.searchTenants(page - 1, size, accountId, searchTenant);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    TenantDTO addTenant(@RequestHeader(value = X_USER_HEADER) UUID accountId, @RequestBody TenantDTO tenantDTO) {
        return tenantExternalAPI.addTenant(tenantDTO, accountId);
    }

    @PatchMapping("/{tenantId}")
    TenantDTO updateTenant(
            @RequestHeader(value = X_USER_HEADER) UUID accountId,
            @PathVariable UUID tenantId,
            @RequestBody TenantDTO tenantDTO) {
        return tenantExternalAPI.patchTenant(tenantId, accountId, tenantDTO);
    }

    @DeleteMapping("/{tenantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTenant(@RequestHeader(value = X_USER_HEADER) UUID accountId, @PathVariable UUID tenantId) {
        tenantExternalAPI.deleteTenant(tenantId, accountId);
    }
}
