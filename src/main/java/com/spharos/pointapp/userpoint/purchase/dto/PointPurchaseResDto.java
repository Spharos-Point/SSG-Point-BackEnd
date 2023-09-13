package com.spharos.pointapp.userpoint.purchase.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PointPurchaseResDto {

    private Long id;
    private Integer purchaseMount;
    private Integer purchasePrice;
    private String uuid;
    private Long branchId;
    private Long pointId;

}
