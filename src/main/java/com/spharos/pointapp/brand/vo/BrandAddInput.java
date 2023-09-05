package com.spharos.pointapp.brand.vo;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BrandAddInput {

    private String brandName;
    private String logoImage;

}
