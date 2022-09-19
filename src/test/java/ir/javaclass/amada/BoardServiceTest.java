package ir.javaclass.amada;

import ir.javaclass.amada.entity.Board;
import ir.javaclass.amada.entity.Card;
import ir.javaclass.amada.entity.UserAccount;
import ir.javaclass.amada.repository.BoardRepository;
import ir.javaclass.amada.repository.CardRepository;
import ir.javaclass.amada.repository.UserRepository;
import ir.javaclass.amada.service.BoardService;
import ir.javaclass.amada.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-18
 * Time: 19:39
 */

@SpringBootTest
@Slf4j
public class BoardServiceTest {
    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CardRepository cardRepository;


    @Test
    public void _01testCreateNewBoard() {
        boardService.createBoard("new board", "create from test service");
    }

    @Test
    public void _02listAllBoard() {
        boardService.list().forEach(boardResponseDto -> {
            log.info(boardResponseDto.toString());
        });
    }

    @Test
    public void _03CreateNewCard() {
        Board newBord = new Board();
        newBord.setId("iddddd");
        newBord.setTitle("new title");

        Card card = new Card();
        card.setId("c1");
        card.setTitle("tttttt");
        cardRepository.save(card);

        Card card1 = new Card();
        card1.setId("c1");
        card1.setTitle("tttttt");
        cardRepository.save(card1);

        newBord.getCardList().add(card);
        newBord.getCardList().add(card1);
        boardRepository.save(newBord);
    }

    @Test
    public void _04findBoardById() {
        Optional<Board> iddddd = boardRepository.findById("iddddd");
        iddddd.ifPresent(board -> log.info(board.toString()));
    }


    @Test
    public void _05SearchCardByTitle() {
        List<Card> searchList = cardRepository.findCardByTitle("tttttt");
        log.info("result: " + searchList.size());
    }

    @Test
    public void _06testSignUp() {
        userService.signUp("mostafa_anbarmoo",
                "Aa123456",
                "mostafa.anbarmoo@gmail.com",
                "Mostafa",
                "Anbarmoo");
    }

    @Test
    public void _07testGetListUsers(){
        List<String> usernames = new ArrayList<>();
        usernames.add("mostafa_anbarmoo");
        usernames.add("22222");
        List<UserAccount> userAccounts = userRepository.findByUsernameIn(usernames);
        log.info("##### size of list: {}", userAccounts.size());

    }

}
