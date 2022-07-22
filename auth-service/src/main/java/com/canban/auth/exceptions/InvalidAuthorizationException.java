package com.canban.auth.exceptions;

public class InvalidAuthorizationException extends RuntimeException {
    public InvalidAuthorizationException(String message) {
        super(message);
    }
}
