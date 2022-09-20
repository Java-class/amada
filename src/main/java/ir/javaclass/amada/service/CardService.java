package ir.javaclass.amada.service;

import ir.javaclass.amada.entity.Card;
import ir.javaclass.amada.model.CardDetailDto;
import ir.javaclass.amada.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-20
 * Time: 17:04
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CardService {

    private final CardRepository cardRepository;

    public List<CardDetailDto> list(String cardTitle) {
        List<Card> searchResult;
        if (cardTitle != null && !cardTitle.isEmpty()) {
            searchResult = cardRepository.findCardByTitleLike(cardTitle);
        } else {
            searchResult = cardRepository.findAll();
        }
        return searchResult.stream().map(CardDetailDto::new).collect(Collectors.toList());
    }
}
