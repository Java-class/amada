package ir.javaclass.amada.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-17
 * Time: 20:44
 */
@Document(collection = "board")
@Getter
@Setter
public class Board {

    private String id;

    private String title;

    private String description;

    private Date creationDate;

    private Date modificationDate;
}
