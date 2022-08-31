package com.canban.web.analytics.exceptions;

import com.canban.api.errors.AppError;
import com.canban.api.errors.ServerNotWorkingError;
import com.canban.api.exceptions.IntegrationException;
import com.canban.api.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ServerNotWorkingError> catchResourceNotFoundException(IntegrationException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ServerNotWorkingError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> catchResourceNotFoundException(ResourceNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

}
