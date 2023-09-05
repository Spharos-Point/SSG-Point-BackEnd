package com.spharos.pointapp.store.vo;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StoreAddInput {

    private Integer id;
    private String storeName;
    private String logoImage;

}
