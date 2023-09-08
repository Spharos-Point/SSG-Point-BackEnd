package com.spharos.pointapp.userpoint.gift.domain;

import com.spharos.pointapp.config.common.BaseTimeEntity;
import com.spharos.pointapp.point.domain.Point;
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
public class PointGift extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;
    private String receiverUuid;

    private GiftType giftType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Point point;
    private String giftMessage;

}