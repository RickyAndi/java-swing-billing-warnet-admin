package com.mycompany.application.enums;

public enum ErrorMessageEnum {

    GENERAL_UNKNOWN_ERROR("Mohon maaf, telah terjadi error"),
    ADMIN_DOES_NOT_EXIST("Akun admin tidak ditemukan, mohon masukan username dan password yang benar");

    public final String message;

    private ErrorMessageEnum(String message) {
        this.message = message;
    }
}
