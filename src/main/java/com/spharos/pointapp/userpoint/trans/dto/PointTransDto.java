package com.spharos.pointapp.userpoint.trans.dto;

import com.spharos.pointapp.affiliatecard.domain.AffiliateCard;
import com.spharos.pointapp.point.domain.Point;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PointTransDto {

    private Long id;
    private Integer transMount;
    private Integer transPoint;
    private Point point;
    private Long branchId;
    private AffiliateCard affiliateCard;
    private String uuid;
}