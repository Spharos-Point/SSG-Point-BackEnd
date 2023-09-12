package com.spharos.pointapp.userpoint.trans.vo;

import com.spharos.pointapp.affiliatecard.domain.AffiliateCard;
import com.spharos.pointapp.point.domain.Point;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PointTransAddRequest {

    private Long id;
    private Integer transMount;
    private Integer transPoint;
    private Point point;
    private AffiliateCard affiliateCard;
    private String uuid;
}