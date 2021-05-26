package com.nuce.service_gara.model.enums;

public enum  EnumRole {

    ROLE_ADMIN("Quản lý"),
    ROLE_RECEPTIONIST("Lễ Tân"),
    ROLE_ADVISOR("Cố vấn dịch vụ");

    private final String shortCode;
    EnumRole(String code) {
        this.shortCode = code;
    }
    public String getShortCode() {
        return this.shortCode;
    }
}
