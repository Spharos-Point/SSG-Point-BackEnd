package com.spharos.pointapp.coupon.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CouponUpdateDto {
    private String couponName;
    private String couponDesc;
    private String usePlace;
    private String couponNum;
    private String couponType;
    private Integer couponValue;
}
