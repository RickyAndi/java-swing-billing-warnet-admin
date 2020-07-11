package com.mycompany.application.exceptions;

public class RepeatPasswordDoesNotMatchException extends Exception {
    public RepeatPasswordDoesNotMatchException(String errorMessage) {
        super(errorMessage);
    }
}
