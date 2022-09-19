package ir.javaclass.amada.repository;

import ir.javaclass.amada.entity.UserAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-18
 * Time: 21:02
 */

@Repository
public interface UserRepository extends MongoRepository<UserAccount, String> {

    Optional<UserAccount> findUserAccountByUsernameAndPasswordHash(String username, String passwordHash);

    List<UserAccount> findByUsernameIn(List<String> usernames);
}
