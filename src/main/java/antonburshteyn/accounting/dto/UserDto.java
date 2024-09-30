package antonburshteyn.accounting.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String login;
    private String firstname;
    private String lastName;
    private String bio; // Описание профиля
    private String profilePictureUrl; // URL аватара
    private Integer  posts; // Количество постов
    private Integer  followers;
    private Integer  following;
    @Singular
    private Set<String> roles;
}
    //	String login;
//    String firstName;
//    String lastName;
//    @Singular
//    Set<String> roles;

