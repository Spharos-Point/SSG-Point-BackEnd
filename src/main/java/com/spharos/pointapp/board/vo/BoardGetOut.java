package com.spharos.pointapp.board.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardGetOut {
    private String title;
    private String context;
    private String loginId;
}
