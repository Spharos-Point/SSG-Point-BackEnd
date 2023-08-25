package com.spharos.pointapp.point.domain;

import com.spharos.pointapp.user.domain.Roll;
import jakarta.persistence.*;

public class point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "status_point")
    private Integer point_status;
    @Column(nullable = false, columnDefinition = "int default 0", name = "point")
    private Integer point;
    @Column(nullable = false, columnDefinition = "int default 1")
    private Boolean used;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10, name = "roll")
    private Roll roll;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 3, name = 'point_type')
    private PointType type;
    private String uuid;
}
