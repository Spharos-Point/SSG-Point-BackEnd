package com.spharos.pointapp.coupon.vo;

import com.spharos.pointapp.partner.domain.Partner;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CouponGetOut {
    private String couponName;
    private String couponDesc;
    private String partnerName;
    private String couponNum;
    private String couponType;
    private Integer couponValue;
}
