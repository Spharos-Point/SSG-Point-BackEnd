package com.spharos.pointapp.admin.board.application;

import com.spharos.pointapp.admin.administrator.domain.Administrator;
import com.spharos.pointapp.admin.administrator.infrastructure.AdministratorRepository;
import com.spharos.pointapp.admin.board.domain.Board;
import com.spharos.pointapp.admin.board.domain.BoardList;
import com.spharos.pointapp.admin.board.dto.BoardCreateDto;
import com.spharos.pointapp.admin.board.infrastructure.BoardListRepository;
import com.spharos.pointapp.admin.board.infrastructure.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImple implements BoardService{

    private final BoardRepository boardRepository;
    private final AdministratorRepository administratorRepository;
    private final BoardListRepository boardListRepository;
    @Override
    public void writeBoard(BoardCreateDto boardCreateDto) {

        Administrator administrator = administratorRepository.findByLoginId(boardCreateDto.getLoginId());
        Board board = new Board();
        if(administrator==null) {
            log.error("Not found Admin");
        } else {
            board = boardRepository.save(
                    Board.builder()
                            .title(boardCreateDto.getTitle())
                            .context(boardCreateDto.getContext())
                            .build()
            );
            BoardList save = boardListRepository.save(
                    BoardList.builder()
                            .board(board)
                            .administrator(administrator)
                            .build()
            );
        }
//        Entity OOD
//        Board board = new Board();
//        board.writeBoard(boardCreateDto.getTitle(), boardCreateDto.getContext());
//        boardRepository.save(board);

    }
}
