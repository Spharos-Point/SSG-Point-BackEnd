package com.spharos.pointapp.point.dto;

import com.spharos.pointapp.point.domain.PointType;
import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointGetDto {
    private Long pointId;
    private Integer totalPoint;
    private Integer point;
    private String pointType;
    private Boolean used;
}
