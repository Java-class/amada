package ir.javaclass.amada.service;

import ir.javaclass.amada.entity.Board;
import ir.javaclass.amada.entity.Card;
import ir.javaclass.amada.entity.UserAccount;
import ir.javaclass.amada.model.BoardResponseDto;
import ir.javaclass.amada.model.RequestContext;
import ir.javaclass.amada.repository.BoardRepository;
import ir.javaclass.amada.repository.CardRepository;
import ir.javaclass.amada.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;

    private final CardRepository cardRepository;

    private final UserRepository userRepository;

    private final RequestContext requestContext;

    public void createBoard(String title, String description) {
        UserAccount creator = userRepository.findById(requestContext.getUsername()).get();
        Board board = new Board();
        board.setId(UUID.randomUUID().toString());
        board.setTitle(title);
        board.setDescription(description);
        board.setCreationDate(new Date());
        board.setCreator(creator);
        boardRepository.save(board);
    }

    public List<BoardResponseDto> list() {
        log.info("username load from request context:{}", requestContext.getUsername());
        return boardRepository.findAll()
                .stream()
                .map(board -> new BoardResponseDto(board.getId(),
                        board.getTitle(),
                        board.getDescription(),
                        board.getCreationDate(),
                        board.getModificationDate(),
                        board.getCreator().getUsername()))
                .collect(Collectors.toList());
    }

    public void deleteById(String boardId) {
        boardRepository.deleteById(boardId);
    }

    public void addCard(String boardId, String cardTitle, List<String> memberIds) {
        Optional<Board> optional = boardRepository.findById(boardId);
        if (optional.isPresent()) {
            List<UserAccount> members = userRepository.findByUsernameIn(memberIds);
            Card card = new Card();
            card.setId(UUID.randomUUID().toString());
            card.setTitle(cardTitle);
            card.setCreationDate(new Date());
            card.setMemberList(members);
            cardRepository.save(card);
        }
    }
}
