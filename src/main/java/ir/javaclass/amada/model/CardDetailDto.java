package ir.javaclass.amada.model;

import ir.javaclass.amada.entity.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-20
 * Time: 15:39
 */
@Getter
@Setter
public class CardDetailDto {
    private String cardId;
    private String title;
    private Date creationDate;
    private Date modificationDate;
    private List<UserDetail> members;

    public CardDetailDto(Card card) {
        this.cardId = card.getId();
        this.title = card.getTitle();
        this.creationDate = card.getCreationDate();
        this.modificationDate = card.getModificationDate();
        this.members = card.getMemberList().stream().map(UserDetail::new).collect(Collectors.toList());
    }
}
