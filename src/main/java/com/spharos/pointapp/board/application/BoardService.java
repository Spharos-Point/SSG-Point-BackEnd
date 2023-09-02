package com.spharos.pointapp.board.application;

import com.spharos.pointapp.board.dto.BoardCreateDto;
import com.spharos.pointapp.board.dto.BoardGetDto;

import java.util.List;

public interface BoardService {
    void createBoard(BoardCreateDto boardCreateDto);

    List<BoardGetDto> getBoards();
}
