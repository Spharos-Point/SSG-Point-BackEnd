package com.spharos.pointapp.pointpartner.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointPartnerOutVo {

    private String partner_id;
    private String partnercategory;
    private String partnername;
    private String partnerlcall;
}
