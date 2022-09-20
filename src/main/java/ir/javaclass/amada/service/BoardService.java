package ir.javaclass.amada.service;

import ir.javaclass.amada.entity.Board;
import ir.javaclass.amada.entity.Card;
import ir.javaclass.amada.entity.UserAccount;
import ir.javaclass.amada.exception.DataNotFoundException;
import ir.javaclass.amada.exception.UserNotFoundException;
import ir.javaclass.amada.model.*;
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

    public void createBoard(String title, String description) throws UserNotFoundException {
        if (requestContext.getUsername() != null && !requestContext.getUsername().isEmpty()) {
            Optional<UserAccount> optionalUserAccount = userRepository.findById(requestContext.getUsername());
            if (optionalUserAccount.isPresent()) {
                Board board = new Board();
                board.setId(UUID.randomUUID().toString());
                board.setTitle(title);
                board.setDescription(description);
                board.setCreationDate(new Date());
                board.setCreator(optionalUserAccount.get());
                boardRepository.save(board);
            } else {
                throw new UserNotFoundException();
            }
        } else {
            throw new UserNotFoundException();
        }
    }

    public GetBoardDetailResponseDto getBoardDetail(String boardId) throws DataNotFoundException {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            return new GetBoardDetailResponseDto(board.getId(),
                    board.getTitle(),
                    board.getDescription(),
                    board.getCreationDate(),
                    board.getModificationDate(),
                    new UserDetail(board.getCreator()),
                    board.getCardList().stream().map(CardDetailDto::new).collect(Collectors.toList()));
        } else {
            throw new DataNotFoundException();
        }
    }

    public void editBoardTitle(String boardId, String newTitle) throws DataNotFoundException {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            board.setTitle(newTitle);
            board.setModificationDate(new Date());
            boardRepository.save(board);
        } else {
            throw new DataNotFoundException();
        }
    }

    public List<BoardResponseDto> list() {
        return boardRepository.findAll()
                .stream()
                .map(board -> new BoardResponseDto(board.getId(),
                        board.getTitle(),
                        board.getDescription(),
                        board.getCreationDate(),
                        board.getModificationDate(),
                        new UserDetail(board.getCreator())))
                .collect(Collectors.toList());
    }

    public void deleteById(String boardId) {
        boardRepository.deleteById(boardId);
    }

    public void addCard(String boardId, String cardTitle, List<String> memberIds) throws DataNotFoundException {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isPresent()) {
            List<UserAccount> members = userRepository.findByUsernameIn(memberIds);
            Card card = new Card();
            card.setId(UUID.randomUUID().toString());
            card.setTitle(cardTitle);
            card.setCreationDate(new Date());
            card.setMemberList(members);
            cardRepository.save(card);
            Board board = optionalBoard.get();
            board.getCardList().add(card);
            board.setModificationDate(new Date());
            boardRepository.save(board);
        } else {
            throw new DataNotFoundException();
        }
    }

    public void deleteCard(String boardId, String cardId) throws DataNotFoundException {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        Optional<Card> optionalCard = cardRepository.findById(cardId);
        if (optionalBoard.isPresent() && optionalCard.isPresent()) {
            Board board = optionalBoard.get();
            board.getCardList().remove(optionalCard.get());
            board.setModificationDate(new Date());
            boardRepository.save(board);
            cardRepository.delete(optionalCard.get());
        } else {
            throw new DataNotFoundException();
        }
    }
}
