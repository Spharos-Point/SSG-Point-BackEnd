package com.spharos.pointapp.coupon.dto;

import com.spharos.pointapp.partner.domain.Partner;
import com.spharos.pointapp.partner.domain.PartnerName;
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
    private Partner partner;
    private String couponNum;
    private String couponType;
    private Integer couponValue;
}
