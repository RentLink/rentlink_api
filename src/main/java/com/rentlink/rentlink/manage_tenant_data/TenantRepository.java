package com.rentlink.rentlink.manage_tenant_data;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

interface TenantRepository extends CrudRepository<Tenant, UUID> {
}
