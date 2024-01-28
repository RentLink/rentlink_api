package com.rentlink.rentlink.manage_unit_data;

import com.rentlink.rentlink.common.ErrorMessage;
import com.rentlink.rentlink.common.enums.ExceptionCode;
import com.rentlink.rentlink.manage_rental_options.RentalOptionFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = UnitEndpoint.class)
@Slf4j
class UnitExceptionHandler {

    @ExceptionHandler(UnitNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFound(UnitNotFoundException ex) {
        return ResponseEntity.status(HttpStatusCode.valueOf(404))
                .body(ErrorMessage.fromExceptionCode(ex.getExceptionCode()));
    }

    @ExceptionHandler(RentalOptionFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFound(RentalOptionFoundException ex) {
        return ResponseEntity.status(HttpStatusCode.valueOf(404))
                .body(ErrorMessage.fromExceptionCode(ex.getExceptionCode()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handleNotFound(RuntimeException ex) {
        log.error("Unexpected error in Unit API", ex);
        return ResponseEntity.status(HttpStatusCode.valueOf(500))
                .body(ErrorMessage.fromExceptionCode(ExceptionCode.UNIT_UNEXPECTED_ERROR));
    }
}
