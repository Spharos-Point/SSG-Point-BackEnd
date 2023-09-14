package com.spharos.pointapp.point.domain;

import lombok.Getter;

@Getter
public enum PointType implements CodeValue{
    EVENT("EV", "이벤트"),
    GIFT("GI", "선물"),
    COUPON("CO", "쿠폰"),
    ATTENDANCE("AT", "출석"),
    ROULETTE("RL", "룰렛"),
    TRANCE("TR", "전환"),
    AFFILIATE("AF", "제휴사"),
    RECEIPT("RE", "영수증"),
    BARCODE("BC","바코드"),
    EXTINCTION("EX", "소멸");

    private String code;
    private String value;

    PointType(String code, String value) {
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
