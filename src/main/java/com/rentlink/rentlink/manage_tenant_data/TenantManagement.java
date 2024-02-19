package com.rentlink.rentlink.manage_tenant_data;

import com.rentlink.rentlink.manage_owner_data.UnitOwnerNotFoundException;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
    public Set<TenantDTO> getTenants() {
        return tenantRepository.findAll().stream().map(tenantMapper::map).collect(Collectors.toSet());
    }

    @Override
    public Set<TenantDTO> searchTenants(Integer page, Integer pageSize, SearchTenant searchTenant) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Specification<Tenant> specification = Specification.where(null);
        if (searchTenant.name() != null) {
            specification = specification.or(
                    TenantSpecification.nameLike(TenantSpecification.TestSpecKeys.NAME, searchTenant.name()));
        }
        if (searchTenant.name() != null) {
            specification = specification.or(
                    TenantSpecification.nameLike(TenantSpecification.TestSpecKeys.SURNAME, searchTenant.surname()));
        }
        if (searchTenant.phone() != null) {
            specification = specification.or(
                    TenantSpecification.nameLike(TenantSpecification.TestSpecKeys.PHONE, searchTenant.phone()));
        }
        if (searchTenant.email() != null) {
            specification = specification.or(
                    TenantSpecification.nameLike(TenantSpecification.TestSpecKeys.EMAIL, searchTenant.email()));
        }

        return tenantRepository
                .findAll(specification, pageable)
                .get()
                .map(tenantMapper::map)
                .collect(Collectors.toSet());
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
