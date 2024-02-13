package com.rentlink.rentlink.manage_tenant_data;

import com.rentlink.rentlink.manage_owner_data.UnitOwnerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
class TenantManagement implements TenantExternalAPI {

    private final TenantRepository tenantRepository;
    private final TenantMapper tenantMapper;

    @Override
    public TenantDTO getTenant(UUID ownerId) {
        return tenantRepository.findById(ownerId).map(tenantMapper::map).orElseThrow(TenantNotFoundException::new);
    }

    @Override
    public Set<TenantDTO> getTenants(Integer page, Integer pageSize) {
        Stream<Tenant> stream;
        if (page != null && pageSize != null) {
            Pageable pageable = PageRequest.of(page, pageSize);
            stream = StreamSupport.stream(tenantRepository.findAll(pageable).spliterator(), false);
        } else {
            stream = tenantRepository.findAll().stream();
        }

        return stream.map(tenantMapper::map).collect(Collectors.toSet());
    }

    @Override
    public TenantDTO addTenant(TenantDTO tenantDTO) {
        return tenantMapper.map(tenantRepository.save(tenantMapper.map(tenantDTO)));
    }

    @Override
    public TenantDTO patchTenant(UUID id, TenantDTO tenantDTO) {
        Tenant tenant = tenantRepository.findById(id).orElseThrow(UnitOwnerNotFoundException::new);
        tenantMapper.update(tenantDTO, tenant);
        return tenantMapper.map(tenantRepository.save(tenant));
    }

    @Override
    public void deleteTenant(UUID ownerId) {
        tenantRepository.deleteById(ownerId);
    }
}
