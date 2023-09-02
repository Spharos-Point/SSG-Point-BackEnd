package com.spharos.pointapp.coupon.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum CouponType implements CodeValue {
    PERCENT("P", "정율"),
    DISCOUNT("D", "정액");

    private String code;
    private String value;

    CouponType(String code, String value) {
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
