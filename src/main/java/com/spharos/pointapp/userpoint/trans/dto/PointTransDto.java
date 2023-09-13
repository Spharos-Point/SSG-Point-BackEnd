package com.spharos.pointapp.userpoint.trans.dto;

import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PointTransDto {

    private Integer transPoint;
    private Long extraId;
    private String uuid;

}