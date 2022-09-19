package ir.javaclass.amada.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-17
 * Time: 20:44
 */
@Document(collection = "board")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Board {
    @Id
    private String id;
    private String title;
    private String description;
    private Date creationDate;
    private Date modificationDate;
    @DocumentReference
    private List<Card> cardList = new ArrayList<>();
    @DocumentReference
    private UserAccount creator;
}
