package com.spharos.pointapp.userpoint.purchase.vo;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PointPurchaseResponse {

    private Long branchId;
    private Integer purchaseMount;
    private Integer purchasePrice;
    private Integer purchasePoint;
}
