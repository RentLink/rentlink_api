package com.rentlink.rentlink.manage_rental_options;

import com.rentlink.rentlink.common.GenericCodeException;
import com.rentlink.rentlink.common.enums.ExceptionCode;

public class RentalOptionFoundException extends GenericCodeException {

    public RentalOptionFoundException() {
        super(ExceptionCode.RENTAL_OPTION_NOT_FOUND);
    }
}
