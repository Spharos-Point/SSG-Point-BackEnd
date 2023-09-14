package com.spharos.pointapp.userpoint.purchase.vo;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PointPurchaseResponse {
    private Long id;
    private String brandName;
    private String branchName;
    private String pointType;
    private Boolean used;
    private Integer purchaseMount;
    private Integer purchasePrice;
    private Integer purchasePoint;

}
