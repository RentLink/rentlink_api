package com.rentlink.rentlink.common;

import com.rentlink.rentlink.common.enums.ExceptionCode;

public record ErrorMessage(String code, String description) {
    public static ErrorMessage fromExceptionCode(ExceptionCode exceptionCode) {
        return new ErrorMessage(exceptionCode.getCode(), exceptionCode.getDescription());
    }
}
