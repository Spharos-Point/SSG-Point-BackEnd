package com.spharos.pointapp.coupon.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponGetDto {
    private String couponName;
    private String couponDesc;
    private String usePlace;
    private String couponNum;
    private String couponType;
    private Integer couponValue;
}
