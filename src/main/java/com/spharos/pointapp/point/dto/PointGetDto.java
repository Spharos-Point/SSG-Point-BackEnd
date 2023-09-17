package com.spharos.pointapp.point.dto;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointGetDto {
    private Integer totalPoint;
    private Integer point;
    private String pointType;
    private Boolean used;
    private String createdAt;
    private String updatedAt;
}
