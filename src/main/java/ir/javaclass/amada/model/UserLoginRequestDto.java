package ir.javaclass.amada.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-18
 * Time: 21:20
 */
@Getter
@Setter
@NoArgsConstructor
public class UserLoginRequestDto {
    private String username;
    private String password;
}
