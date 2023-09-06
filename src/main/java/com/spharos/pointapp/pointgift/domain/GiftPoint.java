package com.spharos.pointapp.pointgift.domain;

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
public class GiftPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "gifter_uuid")
    private String gifterUuid;
    @Column(nullable = false, name = "receiver_uuid")
    private String receiverUuid;
    @Column(nullable = true, name = "gifter_message")
    private String gifterMessage;
    @Column(nullable = false, name = "gift_point")
    private Integer giftPoint;
    @Column(nullable = true, name = "card_Image_Id")
    private Long cardImages;

    @Column(nullable = false)
    @Convert(converter = GiftTypeConverter.class)
    private GiftType giftType;


}