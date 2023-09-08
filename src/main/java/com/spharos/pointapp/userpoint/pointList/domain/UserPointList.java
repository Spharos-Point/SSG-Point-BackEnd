package com.spharos.pointapp.userpoint.pointList.domain;

import com.spharos.pointapp.config.common.BaseTimeEntity;
import com.spharos.pointapp.point.domain.Point;
import com.spharos.pointapp.point.domain.PointType;
import com.spharos.pointapp.point.domain.PointTypeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPointList extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Point point;

    @Column(nullable = false)
    @Convert(converter = PointTypeConverter.class)
    private PointType pointType;
    private String uuid;

}
