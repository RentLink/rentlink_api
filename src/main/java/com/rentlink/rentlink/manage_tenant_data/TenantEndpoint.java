package com.rentlink.rentlink.manage_tenant_data;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/tenant")
@RequiredArgsConstructor
class TenantEndpoint {

    private final TenantExternalAPI tenantExternalAPI;

    @GetMapping("/{tenantId}")
    public TenantDTO getTenant(@PathVariable UUID tenantId) {
        return tenantExternalAPI.getTenant(tenantId);
    }

    @GetMapping("/")
    Set<TenantDTO> getTenants() {
        return tenantExternalAPI.getTenants(null, null);
    }

    @GetMapping(
            value = "",
            params = {"page", "size"})
    Set<TenantDTO> getTenants(
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "size", required = false) int size) {
        return tenantExternalAPI.getTenants(page - 1, size);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    TenantDTO addTenant(@RequestBody TenantDTO tenantDTO) {
        return tenantExternalAPI.addTenant(tenantDTO);
    }

    @PatchMapping("/{tenantId}")
    TenantDTO updateTenant(@PathVariable UUID tenantId, @RequestBody TenantDTO tenantDTO) {
        return tenantExternalAPI.patchTenant(tenantId, tenantDTO);
    }

    @DeleteMapping("/{tenantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTenant(@PathVariable UUID tenantId) {
        tenantExternalAPI.deleteTenant(tenantId);
    }
}
