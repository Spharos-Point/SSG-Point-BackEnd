package com.spharos.pointapp.userpoint.trans.domain;

import com.spharos.pointapp.affiliatecard.domain.AffiliateCard;
import com.spharos.pointapp.brand.domain.Branch;
import com.spharos.pointapp.config.common.BaseTimeEntity;
import com.spharos.pointapp.point.domain.Point;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PointTrans extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer transMount;
    private Integer trnasPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    private Point point;
    @ManyToOne(fetch = FetchType.LAZY)
    private AffiliateCard affiliateCard;

    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    private Branch branch;
}
