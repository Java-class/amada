package ir.javaclass.amada.repository;

import ir.javaclass.amada.entity.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-18
 * Time: 21:31
 */
@Repository
public interface TokenRepository extends MongoRepository<Token, String> {
}
