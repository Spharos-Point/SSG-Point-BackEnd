package com.spharos.pointapp.userpoint.trans.domain;

import com.spharos.pointapp.affiliatecard.domain.AffiliateCard;
import com.spharos.pointapp.config.common.BaseTimeEntity;
import com.spharos.pointapp.point.domain.Point;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PointTrans extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer transMount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Point point;
    @ManyToOne(fetch = FetchType.LAZY)
    private AffiliateCard affiliateCard;

    private String uuid;
}
