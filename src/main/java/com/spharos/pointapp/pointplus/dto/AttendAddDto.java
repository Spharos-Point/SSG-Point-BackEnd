package com.spharos.pointapp.pointplus.dto;

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
