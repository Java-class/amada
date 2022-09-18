package ir.javaclass.amada.repository;

import ir.javaclass.amada.entity.Board;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-17
 * Time: 20:50
 */
@Repository
public interface BoardRepository extends MongoRepository<Board, String> {
}
