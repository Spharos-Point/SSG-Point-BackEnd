package com.spharos.pointapp.coupon.vo;

import com.spharos.pointapp.partner.domain.Partner;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CouponGetOut {
    private Long id;
    private String couponName;
    private String couponDesc;
    private String partnerName;
    private String regDate;
    private String endDate;
    private String couponNum;
    private String couponType;
    private Integer couponValue;
    private String couponLogoImg;
    private String couponImg;
    private String couponValueImg;
}
