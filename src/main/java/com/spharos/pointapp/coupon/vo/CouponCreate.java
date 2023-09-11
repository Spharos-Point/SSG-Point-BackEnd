package com.spharos.pointapp.coupon.vo;

import lombok.*;

@Data
public class CouponCreate {

    private String couponName;
    private String couponDesc;
    private Long partnerId;
    private String couponNum;
    private String couponType;
    private Integer couponValue;
}
