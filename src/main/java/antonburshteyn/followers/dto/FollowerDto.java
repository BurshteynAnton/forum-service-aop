package antonburshteyn.followers.dto;

import lombok.*;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowerDto {
    private String user;
    @Singular
    private Set<String> followers;
    @Singular
    private Set<String> followings;
}
