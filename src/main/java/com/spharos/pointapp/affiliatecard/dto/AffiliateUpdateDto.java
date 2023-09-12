package com.spharos.pointapp.affiliatecard.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AffiliateUpdateDto {
    private String uuid;
    private Long extraId;
    private String affiliateNum;

}
