package com.spharos.pointapp.userpoint.gift.domain;

import lombok.Getter;

@Getter
public enum PointGiftType implements CodeValue{

    GET("G", "적립"),
    WAIT("W", "대기"),
    CANCEL("C", "취소");

    private String code;
    private String value;

    PointGiftType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }

}
