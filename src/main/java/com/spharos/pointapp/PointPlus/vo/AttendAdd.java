package com.spharos.pointapp.PointPlus.vo;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class AttendAdd {
    private String uuid;

    private Integer attendPoint;

    private Integer attendCnt;
}
