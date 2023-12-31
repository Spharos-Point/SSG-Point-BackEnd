package com.spharos.pointapp.userpoint.gift.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PointGiftCreateDto {

    private String receiverLoginId;
    private Integer giftPoint;
    private String pointPassword;
    private String giftMessage;
    private String giftImage;
}
