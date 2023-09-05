package com.spharos.pointapp.affiliatecard.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AffiliateAdd {
    private Long id;
    private Long pintId;
    private String affiliateType;
    private String affiliateNum;
}
