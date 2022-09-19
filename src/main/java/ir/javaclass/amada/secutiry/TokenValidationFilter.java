package ir.javaclass.amada.secutiry;

import ir.javaclass.amada.entity.Token;
import ir.javaclass.amada.entity.UserAccount;
import ir.javaclass.amada.model.RequestContext;
import ir.javaclass.amada.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-19
 * Time: 21:25
 */
@Component
@Order(1)
@Slf4j
@RequiredArgsConstructor
public class TokenValidationFilter implements Filter {

    private final TokenService tokenService;

    private final RequestContext requestContext;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (req.getRequestURI().equals("/api/user/login") || req.getRequestURI().equals("/api/user/signUp")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String tokenStr = req.getHeader("Authorization");
            if (tokenStr != null && !tokenStr.isEmpty()) {
                Optional<Token> optional = tokenService.getToken(tokenStr);
                if (optional.isPresent()) {
                    UserAccount userAccount = optional.get().getUserAccount();
                    requestContext.setUsername(userAccount.getUsername());
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    log.info("access denied!");
                    response.setStatus(403);

                }
            } else {
                response.setStatus(403);
            }
        }
    }
}
