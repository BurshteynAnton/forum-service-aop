package antonburshteyn.post.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import antonburshteyn.accounting.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    private String id;
    private UserDto author; // Вложенный объект пользователя
    private String imageUrl; // URL изображения поста
    private String caption; // Описание поста
    private int likes; // Количество лайков
    private int commentsCount;
    private boolean likedByUser; // Флаг, понравился ли пост пользователю
    @Singular
    Set<String> tags;
    private String dateCreated; // Время создания поста
    @Singular
    private List<CommentDto> comments; // Вложенные комментарии

}
//	String id;
//    String title;
//    String content;
//    String author;
//    LocalDateTime dateCreated;
//    @Singular
//    Set<String> tags;
//    Integer likes;
//    @Singular
//    List<CommentDto> comments;