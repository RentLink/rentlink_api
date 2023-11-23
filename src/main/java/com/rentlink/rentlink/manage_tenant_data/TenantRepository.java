package com.rentlink.rentlink.manage_tenant_data;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

interface TenantRepository extends CrudRepository<Tenant, UUID> {}
