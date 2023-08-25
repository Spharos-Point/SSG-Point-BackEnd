package com.spharos.pointapp.board.application;

import com.spharos.pointapp.admin.administrator.domain.Administrator;
import com.spharos.pointapp.admin.administrator.infrastructure.AdministratorRepository;
import com.spharos.pointapp.board.domain.Board;
import com.spharos.pointapp.board.domain.BoardList;
import com.spharos.pointapp.board.dto.BoardCreateDto;
import com.spharos.pointapp.board.infrastructure.BoardListRepository;
import com.spharos.pointapp.board.infrastructure.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
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

        Administrator administrator = administratorRepository.findByLoginId(boardCreateDto.getLoginId())
                .orElseThrow(()->new IllegalArgumentException("Not found Admin"));
        boardListRepository.save(
                BoardList.builder()
                        .board(
                                boardRepository.save(
                                        Board.builder()
                                                .title(boardCreateDto.getTitle())
                                                .context(boardCreateDto.getContext())
                                                .build()
                                )
                        )
                        .administrator(administrator)
                        .build()
        );
//        Administrator administrator = administratorRepository.findByLoginId(boardCreateDto.getLoginId()).get();
//        Board board = new Board();
//        if(administrator==null) {
//            log.error("Not found Admin");
//        } else {
//            board = boardRepository.save(
//                    Board.builder()
//                            .title(boardCreateDto.getTitle())
//                            .context(boardCreateDto.getContext())
//                            .build()
//            );
//            BoardList save = boardListRepository.save(
//                    BoardList.builder()
//                            .board(board)
//                            .administrator(administrator)
//                            .build()
//            );
//        }
//        Entity OOD
//        Board board = new Board();
//        board.writeBoard(boardCreateDto.getTitle(), boardCreateDto.getContext());
//        boardRepository.save(board);

    }
}
