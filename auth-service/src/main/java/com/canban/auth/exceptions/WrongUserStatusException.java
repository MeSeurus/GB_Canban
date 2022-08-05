package com.canban.auth.exceptions;

public class WrongUserStatusException extends RuntimeException {
    public WrongUserStatusException (String message){super(message);}
}
