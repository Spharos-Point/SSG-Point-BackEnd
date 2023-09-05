package com.spharos.pointapp.point.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointAddDto {

    private Integer point;
    private String pointType;
    private Boolean used;

}
