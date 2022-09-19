package ir.javaclass.amada.service;

import ir.javaclass.amada.entity.Board;
import ir.javaclass.amada.entity.Card;
import ir.javaclass.amada.model.BoardResponseDto;
import ir.javaclass.amada.repository.CardRepository;
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
public class CardService {

    private final CardRepository cardRepository;

    public void createBoard(String title, String description){
        Card card = new Card();
        card.setId(UUID.randomUUID().toString());
        card.setTitle(title);
        card.setDescription(description);
        card.setCreationDate(new Date());
        cardRepository.save(card);
    }
}
