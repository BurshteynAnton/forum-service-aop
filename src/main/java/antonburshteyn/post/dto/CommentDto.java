package antonburshteyn.post.dto;

import java.time.LocalDateTime;

import antonburshteyn.accounting.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private String id;
    private UserDto author;
    private String message; // Текст комментария
    private String createdAt; // Время создания
    private boolean likes; // Лайкнул ли пользователь комментарий

    //	String user;
//    String message;
//    LocalDateTime dateCreated;
//    Integer likes;

}
