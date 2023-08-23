package com.spharos.pointapp.board.application;

import com.spharos.pointapp.board.dto.BoardCreateDto;

public interface BoardService {
    void writeBoard(BoardCreateDto boardCreateDto);
}
