package com.spharos.pointapp.coupon.dto;

import com.spharos.pointapp.coupon.domain.Coupon;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CouponDownDto {

    private Coupon coupon;
    private String uuid;

}
