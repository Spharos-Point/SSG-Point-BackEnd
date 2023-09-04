package com.spharos.pointapp.store.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StoreAddDto {
    private Integer id;
    private String storeName;
    private String logoImage;
}
