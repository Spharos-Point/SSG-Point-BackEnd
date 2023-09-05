package com.spharos.pointapp.store.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreGetDto {

    private Integer id;
    private String storeName;
    private String logoImage;

}
