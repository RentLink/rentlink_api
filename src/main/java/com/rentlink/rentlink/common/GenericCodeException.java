package com.rentlink.rentlink.common;

import com.rentlink.rentlink.common.enums.ExceptionCode;
import lombok.Getter;

@Getter
public class GenericCodeException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    public GenericCodeException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
