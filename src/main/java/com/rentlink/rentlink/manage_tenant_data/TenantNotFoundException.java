package com.rentlink.rentlink.manage_tenant_data;

import com.rentlink.rentlink.common.GenericCodeException;
import com.rentlink.rentlink.common.enums.ExceptionCode;

class TenantNotFoundException extends GenericCodeException {

    public TenantNotFoundException() {
        super(ExceptionCode.TENANT_NOT_FOUND);
    }
}
