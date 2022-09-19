package ir.javaclass.amada.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-18
 * Time: 21:27
 */
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "token")
public class Token {
    @Id
    private String token;
    @DocumentReference
    private UserAccount userAccount;
    private Date creationDate;
    private Date expirationDate;
}
