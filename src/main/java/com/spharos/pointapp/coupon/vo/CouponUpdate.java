package com.spharos.pointapp.coupon.vo;

import com.spharos.pointapp.partner.domain.Partner;
import lombok.*;

@Getter
@Setter

@ToString
public class CouponUpdate {
    private Long couponId;
    private String couponName;
    private String regDate;
    private String endDate;
    private String couponDesc;
    private Long partnerId;
    private String couponNum;
    private String couponType;
    private Integer couponValue;
    private String couponLogoImg;
    private String couponImg;
    private String couponValueImg;
}
