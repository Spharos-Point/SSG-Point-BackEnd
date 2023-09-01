package com.spharos.pointapp.board.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardGetDto {
    private String title;
    private String context;
    private String loginId;
}
