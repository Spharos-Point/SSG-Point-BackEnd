package com.spharos.pointapp.userpoint.purchase.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PointPurchaseDto {

    private Integer purchaseMount;
    private Integer purchasePrice;
    private String uuid;
    private Long branchId;
    private Long pointId;
    private Integer purchasePoint;

}
