package com.spharos.pointapp.userpoint.trans.vo;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PointTransAddRequest {

    private Long extraId;
    private Integer transPoint;

}