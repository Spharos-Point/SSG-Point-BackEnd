package com.spharos.pointapp.affiliatecard.dto;

import com.spharos.pointapp.extra.domain.Extra;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AffiliateAddDto {

    private Long id;
    private Extra extra;
    private String affiliateNum;

}
