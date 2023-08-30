package com.spharos.pointapp.partner.dto;


import com.spharos.pointapp.partner.domain.PartnerName;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartnerCreateDto {
    private String partnerName;
}
