package ir.javaclass.amada.service;

import ir.javaclass.amada.entity.UserAccount;
import ir.javaclass.amada.exception.TokenNotFoundException;
import ir.javaclass.amada.exception.UserAlreadyExistException;
import ir.javaclass.amada.model.RequestContext;
import ir.javaclass.amada.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-18
 * Time: 21:06
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final TokenService tokenService;

    public void signUp(String username, String password, String email, String firstName, String lastName) throws UserAlreadyExistException {
        if (userRepository.findById(username).isEmpty()) {
            UserAccount user = new UserAccount();
            user.setUsername(username);
            user.setPasswordHash(DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)));
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setCreationDate(new Date());
            userRepository.save(user);
        } else {
            throw new UserAlreadyExistException();
        }
    }

    public String login(String username, String password) throws TokenNotFoundException {
        Optional<UserAccount> optional = userRepository.findUserAccountByUsernameAndPasswordHash(username,
                DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)));
        if (optional.isPresent()) {
            return tokenService.generateToken(optional.get());
        } else {
            throw new TokenNotFoundException();
        }
    }

}
