package com.spharos.pointapp.coupon.dto;

import com.spharos.pointapp.partner.domain.Partner;
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
    private Partner partner;
    private String couponNum;
    private String couponType;
    private Integer couponValue;
}
