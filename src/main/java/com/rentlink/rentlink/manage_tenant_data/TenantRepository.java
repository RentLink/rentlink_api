package com.rentlink.rentlink.manage_tenant_data;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface TenantRepository extends JpaRepository<Tenant, UUID>, JpaSpecificationExecutor<Tenant> {

    Optional<Tenant> findByAccountIdAndId(UUID accountId, UUID tenantId);

    void deleteByAccountIdAndId(UUID accountId, UUID tenantId);

    Stream<Tenant> findAllByAccountId(UUID accountId);
}
