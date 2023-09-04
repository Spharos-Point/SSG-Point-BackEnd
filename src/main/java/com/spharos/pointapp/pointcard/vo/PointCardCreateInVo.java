package com.spharos.pointapp.pointcard.vo;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PointCardCreateInVo {

    private String barcode;
    private Integer cvc;
    private String partnerName;
    private String registeredStore;

}
