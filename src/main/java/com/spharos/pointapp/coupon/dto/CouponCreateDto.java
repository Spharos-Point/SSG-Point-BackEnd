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
    private String regDate;
    private String endDate;
    private Partner partner;
    private String couponNum;
    private String couponType;
    private Integer couponValue;
    private String couponLogoImg;
    private String couponImg;
    private String couponValueImg;
}
