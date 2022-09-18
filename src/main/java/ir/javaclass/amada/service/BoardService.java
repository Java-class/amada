package ir.javaclass.amada.service;

import ir.javaclass.amada.entity.Board;
import ir.javaclass.amada.model.BoardResponseDto;
import ir.javaclass.amada.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-17
 * Time: 20:51
 */
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void createBoard(String title, String description){
        Board board = new Board();
        board.setId(UUID.randomUUID().toString());
        board.setTitle(title);
        board.setDescription(description);
        board.setCreationDate(new Date());
        boardRepository.save(board);
    }

    public List<BoardResponseDto> list(){
        return boardRepository.findAll()
                .stream()
                .map(board -> new BoardResponseDto(board.getId(),
                        board.getTitle(),
                        board.getDescription(),
                        board.getCreationDate(),
                        board.getModificationDate()))
                .collect(Collectors.toList());
    }
}
