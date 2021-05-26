package com.nuce.service_gara.model.enums;

public enum EnumStatusReceive {
    WAIT("Chờ tiếp nhận"),
    DOING("Đang tiếp nhận"),
    CHECK("Đang kiểm tra"),
    DONE("Tiếp nhận xong"),
    REJECT("Từ chối tiếp nhận");

    private final String shortCode;
    EnumStatusReceive(String code) {
        this.shortCode = code;
    }
    public String getShortCode() {
        return this.shortCode;
    }

}
