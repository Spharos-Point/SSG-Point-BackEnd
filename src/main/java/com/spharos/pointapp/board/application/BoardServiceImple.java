package com.spharos.pointapp.board.application;

import com.spharos.pointapp.admin.administrator.domain.Administrator;
import com.spharos.pointapp.admin.administrator.infrastructure.AdministratorRepository;
import com.spharos.pointapp.board.domain.Board;
import com.spharos.pointapp.board.domain.BoardList;
import com.spharos.pointapp.board.dto.BoardCreateDto;
import com.spharos.pointapp.board.dto.BoardGetDto;
import com.spharos.pointapp.board.infrastructure.BoardListRepository;
import com.spharos.pointapp.board.infrastructure.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImple implements BoardService{
    private final BoardRepository boardRepository;
    private final AdministratorRepository administratorRepository;
    private final BoardListRepository boardListRepository;
    @Override
    public void createBoard(BoardCreateDto boardCreateDto) {

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
    }
    @Override
    public List<BoardGetDto> getBoards() {
        List<Board> boardList = boardRepository.findAll();
        log.info("{}", boardList);
        List<BoardGetDto> boardGetDtoList = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        boardList.forEach(
                board -> boardGetDtoList.add(
                        mapper.map(board, BoardGetDto.class)
                )
        );

        log.info("{}", boardGetDtoList);
        return boardGetDtoList;
        }
}
