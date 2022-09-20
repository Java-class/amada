package ir.javaclass.amada.controller;

import ir.javaclass.amada.model.CardDetailDto;
import ir.javaclass.amada.model.SortType;
import ir.javaclass.amada.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-20
 * Time: 17:12
 */
@RestController
@RequestMapping(path = "/api/card")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    public ResponseEntity<List<CardDetailDto>> list(@RequestParam("title") String cardTitle, @RequestParam("sortBy") SortType sortType) {
        return ResponseEntity.ok(cardService.list(cardTitle, sortType));
    }
}
