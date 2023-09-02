package com.spharos.pointapp.point.vo;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointOutVo {

    private Integer point;
    private Integer totalPoint;
    private String pointType;
    private Boolean used;

}
