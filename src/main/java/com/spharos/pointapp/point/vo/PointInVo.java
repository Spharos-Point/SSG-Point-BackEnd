package com.spharos.pointapp.point.vo;

import lombok.Data;

@Data
public class PointInVo {

    private Integer point;
    private String pointType;
    private Boolean used;
    private String loginId;

}
