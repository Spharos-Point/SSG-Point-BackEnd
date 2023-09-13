package com.spharos.pointapp.coupon.dto;

import com.spharos.pointapp.coupon.domain.Coupon;
import com.spharos.pointapp.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class CouponDownDto {

    private Coupon coupon;
    private User user;

}
