package ir.javaclass.amada.model;

import ir.javaclass.amada.entity.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-20
 * Time: 15:41
 */
@Getter
public class UserDetail {
    private final String username;
    private final String firstname;
    private final String lastname;
    private final String email;

    public UserDetail(UserAccount userAccount) {
        this.username = userAccount.getUsername();
        this.firstname = userAccount.getFirstName();
        this.lastname = userAccount.getLastName();
        this.email = userAccount.getEmail();
    }
}
