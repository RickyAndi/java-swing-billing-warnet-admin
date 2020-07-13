package com.mycompany.application.exceptions;

public class ComputerClientDoesNotExistException extends Exception {
    public ComputerClientDoesNotExistException(String errorMessage) {

        super(errorMessage);
    }
}
