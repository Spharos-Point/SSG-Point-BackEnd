package com.spharos.pointapp.brand.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandGetDto {

    private Long id;
    private String brandName;
    private String logoImage;

}
