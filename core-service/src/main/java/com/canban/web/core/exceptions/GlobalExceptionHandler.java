package com.canban.web.core.exceptions;

import com.canban.api.errors.AppError;
import com.canban.api.errors.FieldsValidationError;
import com.canban.api.exceptions.ResourceNotFoundException;
import com.canban.api.exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<AppError> catchResourceNotFoundException(ResourceNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<FieldsValidationError> catchValidationException(ValidationException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new FieldsValidationError(ex.getErrorFieldsMessages()),HttpStatus.BAD_REQUEST);
    }

}
