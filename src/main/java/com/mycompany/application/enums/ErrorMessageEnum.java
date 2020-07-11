package com.mycompany.application.enums;

public enum ErrorMessageEnum {

    GENERAL_UNKNOWN_ERROR("Mohon maaf, telah terjadi error"),
    ADMIN_DOES_NOT_EXIST("Akun admin tidak ditemukan, mohon masukan username dan password yang benar"),
    OLD_PASSWORD_DOES_NOT_MATCH("Password lama yang anda masukan salah"),
    NEW_PASSWORD_DOES_NOT_MATCH("Mohon ulangi password baru dengan benar");

    public final String message;

    private ErrorMessageEnum(String message) {
        this.message = message;
    }
}
