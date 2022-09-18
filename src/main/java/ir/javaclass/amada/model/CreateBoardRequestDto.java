package ir.javaclass.amada.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-17
 * Time: 20:57
 */
@Getter
@AllArgsConstructor
public class CreateBoardRequestDto {
    private final String title;
    private final String description;
}
