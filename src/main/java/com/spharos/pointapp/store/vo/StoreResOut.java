package com.spharos.pointapp.store.vo;

import lombok.*;

@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StoreResOut {

    private Integer id;
    private String storeName;
    private String logoImage;

}
