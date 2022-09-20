package ir.javaclass.amada.controller;

import ir.javaclass.amada.exception.DataNotFoundException;
import ir.javaclass.amada.exception.UserNotFoundException;
import ir.javaclass.amada.model.*;
import ir.javaclass.amada.service.BoardService;
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
    public void createBoard(@RequestBody CreateBoardRequestDto requestDto) throws UserNotFoundException {
        boardService.createBoard(requestDto.getTitle(), requestDto.getDescription());
    }

    @GetMapping(value = "/{boardId}")
    public ResponseEntity<GetBoardDetailResponseDto> getBoardDetails(@PathVariable String boardId) throws DataNotFoundException {
        return ResponseEntity.ok(boardService.getBoardDetail(boardId));
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

    @PutMapping("/{boardId}")
    public ResponseEntity<Void> edit(@PathVariable String boardId, @RequestBody EditBoardRequestDto requestDto) throws DataNotFoundException {
        boardService.editBoardTitle(boardId, requestDto.getTitle());
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{boardId}/cards")
    public ResponseEntity<Void> addCard(@PathVariable String boardId, @RequestBody CreateCardRequestDto requestDto) throws DataNotFoundException {
        boardService.addCard(boardId, requestDto.getCardTitle(), requestDto.getMembersId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{boardId}/cards/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable String boardId, @PathVariable String cardId) throws DataNotFoundException {
        boardService.deleteCard(boardId, cardId);
        return ResponseEntity.noContent().build();
    }
}
