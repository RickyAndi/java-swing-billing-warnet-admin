package com.mycompany.application.exceptions;

public class AmountPaidByClientIsNotEnoughException extends Exception {
    public AmountPaidByClientIsNotEnoughException(String errorMessage) {
        super(errorMessage);
    }
}
