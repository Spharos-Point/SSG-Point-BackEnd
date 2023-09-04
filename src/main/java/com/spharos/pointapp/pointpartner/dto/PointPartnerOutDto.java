package com.spharos.pointapp.pointpartner.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class PointPartnerOutDto {
    private String partner_id;
    private String partnercategory;
    private String partnername;
    private String partnerlcall;
}