package com.spharos.pointapp.PointPlus.dto;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendAddDto {
    private String uuid;

    private Integer attendPoint;

    private Integer attendCnt;
}
