package com.spharos.pointapp.board.presentation;


import com.spharos.pointapp.board.application.BoardService;
import com.spharos.pointapp.board.dto.BoardCreateDto;
import com.spharos.pointapp.board.dto.BoardGetDto;
import com.spharos.pointapp.board.vo.BoardGetOut;
import com.spharos.pointapp.board.vo.BoardCreate;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class BoardController {

    private final BoardService boardService;
    @Operation(summary = "1:1상담 답변 등록", description = "1:1상담 답변을 등록합니다.", tags = { "board Controller" })
    @PostMapping("/board")
    public ResponseEntity<String> addBoard(
            @RequestBody BoardCreate BoardCreate) {
        BoardCreateDto boardCreateDto = BoardCreateDto.builder()
                .title(BoardCreate.getTitle())
                .context(BoardCreate.getContext())
                .loginId(BoardCreate.getLoginId())
                .build();
        boardService.createBoard(boardCreateDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    //board 조회
    @Operation(summary = "1:1상담 답변 조회", description = "1:1상담 답변을 조회합니다.", tags = { "board Controller" })
    @GetMapping("/board")
    public List<BoardGetOut> getAllBoards() {
        ModelMapper mapper = new ModelMapper();
        List<BoardGetDto> boardGetDtoList = boardService.getBoards();
        log.info("{}", boardGetDtoList);
        List<BoardGetOut> boardGetOutList = new ArrayList<>();
        boardGetDtoList.forEach(
                boardGetDto -> boardGetOutList.add(
                        mapper.map(boardGetDto, BoardGetOut.class)
                )
        );
        return boardGetOutList;
    }
}


