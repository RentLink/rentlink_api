package com.rentlink.rentlink.manage_tenant_data;

import com.rentlink.rentlink.manage_file_templates.DocTemplate;
import com.rentlink.rentlink.manage_file_templates.FileTemplatesInternalAPI;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tenant")
@RequiredArgsConstructor
class TenantEndpoint {

    private final TenantExternalAPI tenantExternalAPI;
    private final FileTemplatesInternalAPI fileTemplatesInternalAPI;

    @GetMapping("/{tenantId}")
    public TenantDTO getTenant(@PathVariable UUID tenantId) {
        return tenantExternalAPI.getTenant(tenantId);
    }

    @GetMapping("/")
    Set<TenantDTO> getTenants() {
        return tenantExternalAPI.getTenants();
    }

    @GetMapping(value = "")
    Set<TenantDTO> searchTenants(int page, int size, SearchTenant searchTenant) {
        fileTemplatesInternalAPI.generateFromTemplate(
                Map.of("current_date", "2024-01-01", "current_city", "Warszawa"), DocTemplate.CONTRACT);
        return tenantExternalAPI.searchTenants(page - 1, size, searchTenant);
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
