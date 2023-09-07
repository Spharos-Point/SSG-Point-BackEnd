package com.spharos.pointapp.pointgift.vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GiftInVo {

    private Integer point;
    private Long imageId;
    private String giftMessage;
    private String pointPassword;
    private String recipientLoginId;


}
