package ir.javaclass.amada.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-20
 * Time: 15:39
 */
@Setter
@Getter
@AllArgsConstructor
public class GetBoardDetailResponseDto {
    private final String boardId;
    private final String title;
    private final String description;
    private final Date creationDate;
    private final Date modificationDate;
    private final UserDetail creator;
    private final List<CardDetailDto> cards;
}
