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
    @Column(nullable = false, name = "receiver_uuid")
    private String receiverUuid;
    @Column(nullable = false, name= "gift_price")
    private Integer giftPoint;
    @Column(name = "gift_message")
    private String giftMessage;
    @Column(name = "gift_image")
    private String giftImage;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_id")
    private Point point;

    @Column(nullable = false)
    @Convert(converter = PointGiftTypeConverter.class)
    private PointGiftType pointGiftType;

}