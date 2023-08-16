package com.spharos.pointapp.admin.board.presentation;


import com.spharos.pointapp.admin.board.application.BoardService;
import com.spharos.pointapp.admin.board.dto.BoardCreateDto;
import com.spharos.pointapp.admin.board.vo.InBoardCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board")
    public ResponseEntity<String> addBoard(
            @RequestBody InBoardCreate inBoardCreate) {
        BoardCreateDto boardCreateDto = BoardCreateDto.builder()
                .title(inBoardCreate.getTitle())
                .context(inBoardCreate.getContext())
                .loginId(inBoardCreate.getLoginId())
                .build();
        boardService.writeBoard(boardCreateDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
