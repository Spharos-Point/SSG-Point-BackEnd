package com.spharos.pointapp.userpoint.purchase.domain;

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
public class PointPurchase extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer purchaseMount;
    private Integer purchasePrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Point point;

    @ManyToOne(fetch = FetchType.LAZY)
    private Branch branch;
    private String uuid;

}
