package com.spharos.pointapp.pointgift.vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GiftInVo {

    private String gifterUuid;
    private String receiverUuid;
    private String point;
    private String message;
    private String phone;
    private String address;

}
