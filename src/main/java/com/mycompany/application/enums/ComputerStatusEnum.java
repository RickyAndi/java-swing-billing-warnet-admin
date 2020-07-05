package com.mycompany.application.enums;

public enum ComputerStatusEnum {
    INACTIVE(0),
    ACTIVE(1);

    public final Integer value;

    private ComputerStatusEnum(Integer value) {
        this.value = value;
    }
}
