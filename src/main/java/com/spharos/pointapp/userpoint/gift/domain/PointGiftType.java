package com.spharos.pointapp.userpoint.gift.domain;

import lombok.Getter;

@Getter
public enum PointGiftType implements CodeValue{

    ACCEPT("A", "수락"),
    WAIT("W", "대기"),
    REFUSE("R", "거절");

    private final String code;
    private final String value;

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
