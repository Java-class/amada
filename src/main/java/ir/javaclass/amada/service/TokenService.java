package ir.javaclass.amada.service;

import ir.javaclass.amada.entity.Token;
import ir.javaclass.amada.entity.UserAccount;
import ir.javaclass.amada.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-18
 * Time: 21:32
 */
@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public String generateToken(UserAccount userAccount) {
        String tokenStr = userAccount.getUsername() + System.currentTimeMillis() + UUID.randomUUID();
        tokenStr = DigestUtils.md5DigestAsHex(tokenStr.getBytes(StandardCharsets.UTF_8));
        Token token = new Token();
        token.setToken(tokenStr);
        token.setUserAccount(userAccount);
        token.setCreationDate(new Date());
        tokenRepository.save(token);
        return tokenStr;
    }

    public Optional<Token> getToken(String tokenStr){
        return tokenRepository.findById(tokenStr);
    }
}
