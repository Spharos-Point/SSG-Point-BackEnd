package com.spharos.pointapp.affiliatecard.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AffiliateAddDto {

    private Long id;
    private Long pintId;
    private String affiliateType;
    private String affiliateNum;

}
