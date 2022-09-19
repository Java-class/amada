package ir.javaclass.amada.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-18
 * Time: 21:01
 */
@Getter
@NoArgsConstructor
public class UserSignUpRequestDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
