package com.rentlink.rentlink.manage_tenant_data;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TenantManagement implements TenantExternalAPI {

    private final TenantRepository tenantRepository;
    private final TenantMapper tenantMapper;

    @Override
    public TenantDTO getTenant(UUID ownerId) {
        return tenantRepository.findById(ownerId).map(tenantMapper::toDTO).get();
    }

    @Override
    public Set<TenantDTO> getTenants() {
        return StreamSupport.stream(tenantRepository.findAll().spliterator(), false)
                .map(tenantMapper::toDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public TenantDTO addTenant(TenantDTO tenantDTO) {
        return tenantMapper.toDTO(tenantRepository.save(tenantMapper.toDB(tenantDTO)));
    }

    @Override
    public TenantDTO updateTenant(TenantDTO tenantDTO) {
        return tenantMapper.toDTO(tenantRepository.save(tenantMapper.toDB(tenantDTO)));
    }

    @Override
    public void deleteTenant(UUID ownerId) {
        tenantRepository.deleteById(ownerId);
    }
}
