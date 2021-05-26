package com.nuce.service_gara.model.enums;

public enum EnumPosition {
    ADMIN("Quản lý"),
    RECEPTIONIST("Lễ Tân"),
    ADVISOR("Cố vấn dịch vụ");

    private final String shortCode;
    EnumPosition(String code) {
        this.shortCode = code;
    }
    public String getShortCode() {
        return this.shortCode;
    }
}
