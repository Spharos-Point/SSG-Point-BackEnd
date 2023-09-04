package com.spharos.pointapp.PointPlus.dto;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RouletteAddDto {
    private String uuid;
    private String pointId;
    private Integer roulettePoint;
}
