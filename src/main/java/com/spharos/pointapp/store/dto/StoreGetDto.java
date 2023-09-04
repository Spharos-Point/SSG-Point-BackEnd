package com.spharos.pointapp.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreGetDto {

    private Integer id;
    private String storeName;
    private String logoImage;

}
