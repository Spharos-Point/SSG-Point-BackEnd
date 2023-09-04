package com.spharos.pointapp.pointpartner.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PointPartnerCreateDto {
    private String partner_id;
    private String partnercategory;
    private String partnername;
    private String partnerlcall;
}
