package com.mycompany.application.enums;

public enum MessageEnum {

    UPDATE_SETTING_SUCCESS("Setting telah berhasil diubah"),
    UPDATE_PROFILE_SUCCESS("Profile telah berhasil diubah");

    public String message;

    private MessageEnum(String message) {
        this.message = message;
    }
}
