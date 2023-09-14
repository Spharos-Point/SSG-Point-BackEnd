package com.spharos.pointapp.brand.vo;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandResOut {

    private Long id;
    private String brandName;
    private String logoImage;

}
