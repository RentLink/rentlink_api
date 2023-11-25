package com.rentlink.rentlink.manage_tenant_data;

import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @ResponseStatus(HttpStatus.CREATED)
    TenantDTO addTenant(@RequestBody TenantDTO tenantDTO) {
        return tenantExternalAPI.addTenant(tenantDTO);
    }

    @PatchMapping("/{tenantId}")
    TenantDTO updateTenant(@RequestBody TenantDTO tenantDTO) {
        return tenantExternalAPI.updateTenant(tenantDTO);
    }

    @DeleteMapping("/{tenantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTenant(@PathVariable UUID tenantId) {
        tenantExternalAPI.deleteTenant(tenantId);
    }
}
