package com.mycompany.application.exceptions;

public class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
