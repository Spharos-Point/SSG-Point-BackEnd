package com.spharos.pointapp.userpoint.trans.vo;

import lombok.*;

@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointTransResponse {

    private Long id;
    private Integer transPoint;
    private Long pointId;
    private Long extraId;

}
