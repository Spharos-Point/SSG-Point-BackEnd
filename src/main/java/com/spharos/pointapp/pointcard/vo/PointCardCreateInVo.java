package com.spharos.pointapp.pointcard.vo;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PointCardCreateInVo {

    private String cardNumber;
    private String cvc;
    private Integer brandId;
    private Long branchId;
}
