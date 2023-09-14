package com.spharos.pointapp.userpoint.trans.dto;

import com.spharos.pointapp.extra.domain.Extra;
import com.spharos.pointapp.point.domain.Point;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PointTransResDto {

    private Long id;
    private Integer transPoint;
    private Point point;
    private Extra extra;
}
