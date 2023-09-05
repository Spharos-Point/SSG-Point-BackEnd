package com.spharos.pointapp.pointplus.vo;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class AttendAdd {
    private String uuid;

    private Integer attendPoint;

    private Integer attendCnt;
}
