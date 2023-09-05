package com.spharos.pointapp.pointplus.domain;

import com.spharos.pointapp.config.common.BaseTimeEntity2;
import com.spharos.pointapp.point.domain.Point;
import com.spharos.pointapp.user.domain.User;
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
public class Attend extends BaseTimeEntity2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uuid", referencedColumnName = "uuid")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_id")
    private Point point;

    @Column(nullable = false, name = "attend_point")
    private Integer attendPoint;

//    총 출석 일수
    @Column(nullable = false, name = "attend_cnt")
    private Integer attendCnt;



}
