package com.rentlink.rentlink.manage_tenant_data;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

class TenantSpecification {
    public static Specification<Tenant> nameLike(TestSpecKeys key, String value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(key.name), value);
    }

    @RequiredArgsConstructor
    enum TestSpecKeys {
        ACCOUNT_ID("accountId"),
        NAME("name"),
        SURNAME("surname"),
        EMAIL("email"),
        PHONE("phone");

        private final String name;
    }
}
