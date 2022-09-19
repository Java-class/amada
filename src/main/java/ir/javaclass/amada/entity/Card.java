package ir.javaclass.amada.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;
import java.util.List;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-18
 * Time: 19:29
 */
@Document(collection = "card")
@Getter
@Setter
@ToString
public class Card {
    @Id
    private String id;
    private String title;
    private String description;
    private Date creationDate;
    private Date modificationDate;
    @DocumentReference
    private List<UserAccount> memberList;
}
