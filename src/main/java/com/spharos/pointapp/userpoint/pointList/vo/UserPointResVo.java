package com.spharos.pointapp.userpoint.pointList.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPointResVo {

    private Long id;
    private Long pointId;
    private String pointType;
    private Long pointTypeById;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
