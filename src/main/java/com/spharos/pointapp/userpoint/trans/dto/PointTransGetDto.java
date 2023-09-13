package com.spharos.pointapp.userpoint.trans.dto;

import com.spharos.pointapp.point.domain.Point;
import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PointTransGetDto {
    private Long id;
    private Integer transPoint;
    private Point point;
    private Long extraId;
    private String uuid;
}