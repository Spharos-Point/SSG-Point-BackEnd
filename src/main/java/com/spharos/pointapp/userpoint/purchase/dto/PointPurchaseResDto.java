package com.spharos.pointapp.userpoint.purchase.dto;

import com.spharos.pointapp.config.common.BaseResponse;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PointPurchaseResDto {

    private Long id;
    private Long pointId;
    private String brandName;
    private String branchName;
    private String pointType;
    private Boolean used;
    private Integer purchaseMount;
    private Integer purchasePrice;
    private Integer purchasePoint;

}
