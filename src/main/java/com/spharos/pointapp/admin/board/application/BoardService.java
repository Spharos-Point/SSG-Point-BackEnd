package com.spharos.pointapp.admin.board.application;

import com.spharos.pointapp.admin.board.dto.BoardCreateDto;

public interface BoardService {
    void writeBoard(BoardCreateDto boardCreateDto);
}
