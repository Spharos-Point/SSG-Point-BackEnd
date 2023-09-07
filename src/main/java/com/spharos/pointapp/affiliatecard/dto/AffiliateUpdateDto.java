package com.spharos.pointapp.affiliatecard.dto;

import com.spharos.pointapp.extra.domain.Extra;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AffiliateUpdateDto {
    private String uuid;
    private Long extraId;
    private String affiliateNum;

}
