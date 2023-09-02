package com.spharos.pointapp.coupon.dto;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponCreateDto {
    private String couponName;
    private String couponDesc;
    private String usePlace;
    private String couponNum;
    private String couponType;
    private Integer couponValue;
}
