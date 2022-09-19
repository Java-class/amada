package ir.javaclass.amada.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-17
 * Time: 21:05
 */
@Getter
@AllArgsConstructor
@ToString
public class BoardResponseDto {
    private final String boardId;
    private final String title;
    private final String description;
    private final Date creationDate;
    private final Date modificationDate;
    private final String creatorUsername;
}
