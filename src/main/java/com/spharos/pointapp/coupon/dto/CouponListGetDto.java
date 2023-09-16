package com.spharos.pointapp.coupon.dto;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CouponListGetDto {
    private Long id;
    private Long couponId;
    private Long userId;
    private Boolean couponStat;
}
