package com.spharos.pointapp.point.domain;

import com.spharos.pointapp.config.common.BaseTimeEntity;
import com.spharos.pointapp.user.domain.User;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate // 더티체킹 변경된 필드만 update
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

    @Column(nullable = false, name = "uuid")
    private String uuid;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "uuid", referencedColumnName = "uuid")
//    private User user;

}
