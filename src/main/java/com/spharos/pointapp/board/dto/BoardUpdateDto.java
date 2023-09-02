package com.spharos.pointapp.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor

public class BoardUpdateDto {
    private String title;
    private String context;
    private String loginId;
}
