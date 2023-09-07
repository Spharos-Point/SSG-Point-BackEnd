package com.spharos.pointapp.affiliatecard.dto;

import com.spharos.pointapp.extra.domain.Extra;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AffiliateUpdateDto {
    private Long affiliateId;
    private Long extraId;
    private String affiliateNum;

}
