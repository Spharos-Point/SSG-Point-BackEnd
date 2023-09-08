package com.spharos.pointapp.userpoint.purchase.vo;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PointPurchaseAddRequest {

    private String uuid;
    private Long branchId;
    private Integer purchaseMount;
    private Integer purchasePrice;
    private Integer purchasePoint;

}
