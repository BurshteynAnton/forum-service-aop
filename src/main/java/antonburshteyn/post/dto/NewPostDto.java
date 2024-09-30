package antonburshteyn.post.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Singular;

@Getter
public class NewPostDto {
	String title;
	String content;
	@Singular
	private Set<String> tags;
}
