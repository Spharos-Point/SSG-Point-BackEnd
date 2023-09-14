package com.spharos.pointapp.point.domain;

import com.spharos.pointapp.config.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Point extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "total_point")
    private Integer totalPoint;
    @Column(nullable = false, name = "point", columnDefinition = "int default 0")
    private Integer point;
    @Column(nullable = false, name = "used", columnDefinition = "boolean default false")
    private Boolean used;

    @Column(nullable = false)
    @Convert(converter = PointTypeConverter.class)
    private PointType pointType;

}
