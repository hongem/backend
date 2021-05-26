package com.nuce.service_gara.model.enums;

public enum EnumStatusRepair {
    DOING("Đang sửa chữa"),
    DONE("Sửa chữa xong"),
    LEAVE("Xe rời bến");

    private final String shortCode;
    EnumStatusRepair(String code) {
        this.shortCode = code;
    }
    public String getShortCode() {
        return this.shortCode;
    }
}
