package com.canban.web.analytics.exceptions;

import com.canban.api.errors.ServerNotWorkingError;
import com.canban.api.exceptions.ServerNotWorkingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ServerNotWorkingError> catchResourceNotFoundException(ServerNotWorkingException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ServerNotWorkingError(HttpStatus.REQUEST_TIMEOUT.value(), ex.getMessage()), HttpStatus.REQUEST_TIMEOUT);
    }

}
