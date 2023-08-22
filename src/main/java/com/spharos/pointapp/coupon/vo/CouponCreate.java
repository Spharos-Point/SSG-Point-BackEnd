package com.spharos.pointapp.coupon.vo;

import lombok.*;

public class CouponCreate {
    enum CouponType {  // 'public'로 선언
        PERCENT, DISCOUNT
    }
    private String couponName;
    private String couponDesc;
    private String usePlace;
    private String couponNum;
    private CouponType CouponType;
    private Integer couponValue;
}
