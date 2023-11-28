package com.rentlink.rentlink.manage_tenant_data;

import com.rentlink.rentlink.common.ErrorMessage;
import com.rentlink.rentlink.common.enums.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = TenantEndpoint.class)
@Slf4j
class TenantExceptionHandler {

    @ExceptionHandler(TenantNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFound(TenantNotFoundException ex) {
        return ResponseEntity.status(HttpStatusCode.valueOf(404))
                .body(ErrorMessage.fromExceptionCode(ex.getExceptionCode()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handleNotFound(RuntimeException ex) {
        log.error("Unexpected error in Tenant API", ex);
        return ResponseEntity.status(HttpStatusCode.valueOf(500))
                .body(ErrorMessage.fromExceptionCode(ExceptionCode.TENANT_UNEXPECTED_ERROR));
    }
}
