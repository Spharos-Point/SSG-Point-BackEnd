package com.spharos.pointapp.userpoint.gift.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
public class PointGiftLastOutVo {

    private Long giftId;
    private String senderLoginId;
    private String senderName;
    private Integer point;
    private String giftImage;
    private String giftMessage;
    private LocalDateTime createdDate;
}
