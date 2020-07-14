package com.mycompany.application.enums;

public enum  SocketEventNameEnum {

    CLIENT_LOGGED_IN("CLIENT_LOGGED_IN"),
    CLIENT_LOGGED_OUT("CLIENT_LOGGED_OUT");

    public final String name;

    private SocketEventNameEnum(String name) {
        this.name = name;
    }
}
