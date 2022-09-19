package ir.javaclass.amada.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-19
 * Time: 22:21
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Setter
@Getter
public class RequestContext {
    private String username;
}
