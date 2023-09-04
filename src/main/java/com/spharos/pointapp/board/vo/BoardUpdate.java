package com.spharos.pointapp.board.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BoardUpdate {
    private String title;
    private String context;
    private String loginId;
}
