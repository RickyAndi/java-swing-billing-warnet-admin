package com.mycompany.application.exceptions;

public class ComputerNameIsNotSetException extends Exception {
    public ComputerNameIsNotSetException(String errorMessage) {
        super(errorMessage);
    }
}
