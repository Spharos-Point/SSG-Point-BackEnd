package com.spharos.pointapp.coupon.vo;

import lombok.*;

@Getter
@ToString
public class CouponUpdate {
    private Long couponId;
    private String couponName;
    private String couponDesc;
    private String usePlace;
    private String couponNum;
    private String couponType;
    private Integer couponValue;
}
