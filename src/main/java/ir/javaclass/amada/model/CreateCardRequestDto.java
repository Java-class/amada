package ir.javaclass.amada.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-18
 * Time: 22:21
 */
@Getter
@Setter
@NoArgsConstructor
public class CreateCardRequestDto {
    private String cardTitle;
    private String description;
    private List<String> membersId;
}
