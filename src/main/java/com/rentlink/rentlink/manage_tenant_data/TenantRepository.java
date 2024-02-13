package com.rentlink.rentlink.manage_tenant_data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface TenantRepository extends JpaRepository<Tenant, UUID> {}
