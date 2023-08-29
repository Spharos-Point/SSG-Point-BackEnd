package com.spharos.pointapp.pointcard.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointCardOutVo {

    private String cardNum;
    private String partnerName;

}
