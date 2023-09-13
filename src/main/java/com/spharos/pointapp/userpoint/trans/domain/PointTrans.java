package com.spharos.pointapp.userpoint.trans.domain;

import com.spharos.pointapp.config.common.BaseTimeEntity;
import com.spharos.pointapp.extra.domain.Extra;
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
    private Integer transPoint;
    @ManyToOne(fetch = FetchType.LAZY)
    private Point point;
    @ManyToOne(fetch = FetchType.LAZY)
    private Extra extra;

}