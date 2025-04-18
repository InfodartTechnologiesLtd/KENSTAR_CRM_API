package com.infodart.kenstar_crm.exceptions;

public class UserAlreadyInactiveException extends RuntimeException {
    public UserAlreadyInactiveException(String message) {
        super(message);
    }
}
