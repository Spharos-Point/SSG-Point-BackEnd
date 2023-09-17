package com.spharos.pointapp.point.vo;

import lombok.*;

@Builder
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PointOutVo {

    private Integer point;
    private Integer totalPoint;
    private String pointType;
    private Boolean used;
    private String createdAt;
    private String updatedAt;

}
