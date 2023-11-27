package com.rentlink.rentlink.manage_owner_data;

import com.rentlink.rentlink.common.GenericCodeException;
import com.rentlink.rentlink.common.enums.ExceptionCode;

public class UnitOwnerNotFoundException extends GenericCodeException {

    public UnitOwnerNotFoundException() {
        super(ExceptionCode.UNIT_OWNER_NOT_FOUND);
    }
}
