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
    @Column(nullable = false, length = 20, name = "barcode")
    private String barcode;
    @Column(nullable = false, length = 20, name = "partner_name")
    private String partnerName;
    @Column(nullable = false, length = 100, name = "uuid")
    private String uuid;

}
