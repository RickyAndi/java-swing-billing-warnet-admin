package com.mycompany.application.enums;

public enum TransactionStatusEnum {

    ACTIVE(0),
    NOT_PAID(1),
    PAID(2);

    public final Integer value;

    private TransactionStatusEnum(Integer value) {
        this.value = value;
    }
}
