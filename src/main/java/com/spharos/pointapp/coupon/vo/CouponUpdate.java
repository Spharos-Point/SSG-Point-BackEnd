package com.spharos.pointapp.coupon.vo;

import com.spharos.pointapp.partner.domain.Partner;
import com.spharos.pointapp.partner.domain.PartnerName;
import lombok.*;

@Getter
@Setter
@ToString
public class CouponUpdate {
    private Long couponId;
    private String couponName;
    private String couponDesc;
    private Partner partner;
    private String couponNum;
    private String couponType;
    private Integer couponValue;
}
