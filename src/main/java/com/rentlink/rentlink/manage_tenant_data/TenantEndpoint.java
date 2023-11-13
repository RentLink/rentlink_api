package com.rentlink.rentlink.manage_tenant_data;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/tenant")
@RequiredArgsConstructor
public class TenantEndpoint {


    private final TenantExternalAPI tenantExternalAPI;

    @GetMapping("/{tenantId}")
    public TenantDTO getTenant(@PathVariable UUID tenantId) {
        return tenantExternalAPI.getTenant(tenantId);
    }

    @GetMapping("/")
    Set<TenantDTO> getTenants() {
        return tenantExternalAPI.getTenants();
    }

    @PostMapping("/")
    TenantDTO addTenant(@RequestBody TenantDTO tenantDTO) {
        return tenantExternalAPI.addTenant(tenantDTO);
    }

    @PutMapping("/{tenantId}")
    TenantDTO updateTenant(@RequestBody TenantDTO tenantDTO) {
        return tenantExternalAPI.updateTenant(tenantDTO);
    }

    @DeleteMapping("/{tenantId}")
    void deleteTenant(@PathVariable UUID tenantId) {
        tenantExternalAPI.deleteTenant(tenantId);
    }
}
