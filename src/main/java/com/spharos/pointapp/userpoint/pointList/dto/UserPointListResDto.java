package com.spharos.pointapp.userpoint.pointList.dto;

import com.spharos.pointapp.point.domain.Point;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserPointListResDto {

    private Long id;
    private Long pointId;
    private String pointType;
    private Long pointTypeById;
    private String createAt;
    private String updateAt;

}
