package com.mycompany.application.enums;

public enum SettingNameEnum {

    NAMA_WARNET("NAMA_WARNET"),
    ALAMAT_WARNET("ALAMAT_WARNET"),
    BIAYA_PER_JAM("BIAYA_PER_JAM"),
    BIAYA_DITAMBAHKAN_SETIAP_BERAPA_MENIT("BIAYA_DITAMBAHKAN_SETIAP_BERAPA_MENIT");


    public final String name;

    private SettingNameEnum(String name) {
        this.name = name;
    }
}
