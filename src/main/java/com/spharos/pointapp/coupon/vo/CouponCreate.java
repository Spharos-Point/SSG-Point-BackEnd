package com.spharos.pointapp.coupon.vo;

import jakarta.persistence.Column;
import lombok.*;

@Data
public class CouponCreate {

    private String couponName;
    private String couponDesc;
    private Long partnerId;
    private String regDate;
    private String endDate;
    private String couponNum;
    private String couponType;
    private Integer couponValue;
    private String couponLogoImg;
    private String couponImg;
    private String couponValueImg;
}
