package com.mycompany.application.enums;

public enum CurrencyAbbrevationEnum {

    RUPIAH("Rp");

    public final String currencyAbbr;

    private CurrencyAbbrevationEnum(String currencyAbbr) {
        this.currencyAbbr = currencyAbbr;
    }
}
