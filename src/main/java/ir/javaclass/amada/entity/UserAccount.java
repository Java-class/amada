package ir.javaclass.amada.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-18
 * Time: 20:57
 */
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "user-account")
public class UserAccount {
    @Id
    private String username;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private String email;
    private Date creationDate;
}
