package com.mycompany.application.enums;

public enum ErrorMessage {

    ADMIN_DOES_NOT_EXIST("Akun admin tidak ditemukan, mohon masukan username dan password yang benar");

    public final String message;

    private ErrorMessage(String message) {
        this.message = message;
    }
}
