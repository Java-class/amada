package ir.javaclass.amada.controller;

import ir.javaclass.amada.model.BoardResponseDto;
import ir.javaclass.amada.model.CreateBoardRequestDto;
import ir.javaclass.amada.model.CreateCardRequestDto;
import ir.javaclass.amada.service.BoardService;
import ir.javaclass.amada.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-17
 * Time: 20:54
 */
@RestController
@RequestMapping(path = "/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping()
    public void createBoard(@RequestBody CreateBoardRequestDto requestDto) {
        boardService.createBoard(requestDto.getTitle(), requestDto.getDescription());
    }

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> list() {
        return ResponseEntity.ok(boardService.list());
    }

    @DeleteMapping(value = "/{boardId}")
    public ResponseEntity<Void> delete(@PathVariable String boardId) {
        boardService.deleteById(boardId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{boardId}/cards")
    public ResponseEntity<Void> addCard(@PathVariable String boardId, @RequestBody CreateCardRequestDto requestDto){
//        boardService
        return ResponseEntity.ok().build();
    }
}
