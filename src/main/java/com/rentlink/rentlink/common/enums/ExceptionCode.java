package com.rentlink.rentlink.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {
    UNIT_OWNER_NOT_FOUND("UNIT_OWNER_NOT_FOUND", "Unit owner not found"),
    UNIT_OWNER_UNEXPECTED_ERROR("UNIT_OWNER_UNEXPECTED_ERROR", "Unexpected error"),

    TENANT_NOT_FOUND("TENANT_NOT_FOUND", "Tenant not found"),
    TENANT_UNEXPECTED_ERROR("TENANT_UNEXPECTED_ERROR", "Unexpected error"),

    UNIT_NOT_FOUND("UNIT_NOT_FOUND", "Unit not found"),
    UNIT_UNEXPECTED_ERROR("UNIT_UNEXPECTED_ERROR", "Unexpected error");

    private final String code;
    private final String description;
}
