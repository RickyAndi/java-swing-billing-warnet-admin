package com.mycompany.application.exceptions;

public class OldPasswordDoesNotMatchException extends Exception {
    public OldPasswordDoesNotMatchException(String errorMessage) {
        super(errorMessage);
    }
}
