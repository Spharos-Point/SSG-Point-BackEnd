package com.spharos.pointapp.pointcard.domain;

import com.spharos.pointapp.config.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PointCard extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20, name = "cardnumber")
    private String cardnumber;
    @Column(nullable = false, length = 100, name = "uuid")
    private String uuid;
    private String cvc;

    private Integer brandId;
    private Long branchId;

}
