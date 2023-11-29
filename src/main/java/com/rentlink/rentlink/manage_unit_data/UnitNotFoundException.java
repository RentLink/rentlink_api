package com.rentlink.rentlink.manage_unit_data;

import com.rentlink.rentlink.common.GenericCodeException;
import com.rentlink.rentlink.common.enums.ExceptionCode;

public class UnitNotFoundException extends GenericCodeException {

    public UnitNotFoundException() {
        super(ExceptionCode.UNIT_NOT_FOUND);
    }
}
