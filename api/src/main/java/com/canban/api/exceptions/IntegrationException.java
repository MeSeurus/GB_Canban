package com.canban.api.exceptions;

public class IntegrationException extends RuntimeException{
    public IntegrationException(String message) {
        super(message);
    }
}
