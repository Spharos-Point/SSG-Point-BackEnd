package com.spharos.pointapp.userpoint.gift.vo;

import lombok.*;

@Getter
@ToString
public class PointGiftInVo {

    private String senderLoginId;
    private Integer giftPoint;
    private String pointPassword;
    private String giftMessage;
    private String giftImage;
}
