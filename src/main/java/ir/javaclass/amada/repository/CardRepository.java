package ir.javaclass.amada.repository;

import ir.javaclass.amada.entity.Board;
import ir.javaclass.amada.entity.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-17
 * Time: 20:50
 */
@Repository
public interface CardRepository extends MongoRepository<Card, String> {

    List<Card> findCardByTitle(String title);

    List<Card> findCardByTitleLike(String title);
}
