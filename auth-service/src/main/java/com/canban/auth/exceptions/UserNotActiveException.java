package com.canban.auth.exceptions;

public class UserNotActiveException extends RuntimeException{
    public UserNotActiveException (String message){
        super(message);
    }
}
