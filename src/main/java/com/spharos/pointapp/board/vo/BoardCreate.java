package com.spharos.pointapp.board.vo;

import lombok.*;

@Getter
@Builder
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardCreate {
    private String title;
    private String context;
    private String loginId;
}
