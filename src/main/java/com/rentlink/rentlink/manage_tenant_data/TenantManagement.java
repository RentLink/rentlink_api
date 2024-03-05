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
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
class TenantManagement implements TenantExternalAPI {

    private final TenantRepository tenantRepository;
    private final TenantMapper tenantMapper;

    @Transactional(readOnly = true)
    @Override
    public TenantDTO getTenant(UUID tenantId, UUID accountId) {
        return tenantRepository
                .findByAccountIdAndId(accountId, tenantId)
                .map(tenantMapper::map)
                .orElseThrow(TenantNotFoundException::new);
    }


    @Transactional(readOnly = true)
    @Override
    public Set<TenantDTO> getTenants(UUID accountId) {
        return tenantRepository
                .findAllByAccountId(accountId)
                .map(tenantMapper::map)
                .collect(Collectors.toSet());
    }

    @Transactional(readOnly = true)
    @Override
    public Set<TenantDTO> quickSearch(UUID accountId, String value) {
        Specification<Tenant> specification = Specification.where(null);
        specification = specification.and(
                TenantSpecification.nameLike(TenantSpecification.TestSpecKeys.ACCOUNT_ID, accountId.toString()));

        if (value != null) {
            specification =
                    specification.or(TenantSpecification.nameLike(TenantSpecification.TestSpecKeys.NAME, value));

            specification =
                    specification.or(TenantSpecification.nameLike(TenantSpecification.TestSpecKeys.SURNAME, value));

            specification =
                    specification.or(TenantSpecification.nameLike(TenantSpecification.TestSpecKeys.PHONE, value));

            specification =
                    specification.or(TenantSpecification.nameLike(TenantSpecification.TestSpecKeys.EMAIL, value));
        }

        return tenantRepository.findAll(specification).stream()
                .map(tenantMapper::map)
                .collect(Collectors.toSet());
    }

    @Transactional(readOnly = true)
    @Override
    public Set<TenantDTO> search(Integer page, Integer pageSize, UUID accountId, SearchTenant searchTenant) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Specification<Tenant> specification = Specification.where(null);
        specification = specification.and(
                TenantSpecification.nameLike(TenantSpecification.TestSpecKeys.ACCOUNT_ID, accountId.toString()));

        if (searchTenant.name() != null) {
            specification = specification.and(
                    TenantSpecification.nameLike(TenantSpecification.TestSpecKeys.NAME, searchTenant.name()));
        }
        if (searchTenant.name() != null) {
            specification = specification.and(
                    TenantSpecification.nameLike(TenantSpecification.TestSpecKeys.SURNAME, searchTenant.surname()));
        }
        if (searchTenant.phone() != null) {
            specification = specification.and(
                    TenantSpecification.nameLike(TenantSpecification.TestSpecKeys.PHONE, searchTenant.phone()));
        }
        if (searchTenant.email() != null) {
            specification = specification.and(
                    TenantSpecification.nameLike(TenantSpecification.TestSpecKeys.EMAIL, searchTenant.email()));
        }

        return tenantRepository
                .findAll(specification, pageable)
                .get()
                .map(tenantMapper::map)
                .collect(Collectors.toSet());
    }

    @Transactional
    @Override
    public TenantDTO addTenant(TenantDTO tenantDTO, UUID accountId) {
        Tenant tenant = tenantMapper.map(tenantDTO);
        tenant.setAccountId(accountId);
        return tenantMapper.map(tenantRepository.save(tenant));
    }

    @Transactional
    @Override
    public TenantDTO patchTenant(UUID id, UUID accountId, TenantDTO tenantDTO) {
        Tenant tenant =
                tenantRepository.findByAccountIdAndId(accountId, id).orElseThrow(UnitOwnerNotFoundException::new);
        tenantMapper.update(tenantDTO, tenant);
        return tenantMapper.map(tenantRepository.save(tenant));
    }

    @Transactional
    @Override
    public void deleteTenant(UUID ownerId, UUID accountId) {
        tenantRepository.deleteByAccountIdAndId(accountId, ownerId);
    }
}
