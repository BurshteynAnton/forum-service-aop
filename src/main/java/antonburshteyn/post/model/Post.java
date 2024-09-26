package antonburshteyn.post.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id; // Идентификатор поста с автоматической генерацией UUID

	private String title;
	private String content;
	private String author;
	private LocalDateTime dateCreated = LocalDateTime.now();

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "post_tags", joinColumns = @JoinColumn(name = "post_id"))
	@Column(name = "tag")
	private Set<String> tags = new HashSet<>();

	private int likes;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private List<Comment> comments = new ArrayList<>();

	public Post(String title, String content, String author, Set<String> tags) {
		this.title = title;
		this.content = content;
		this.author = author;
		this.tags = tags;
	}

	public void addLike() {
		likes++;
	}

	public boolean addTag(String tag) {
		return tags.add(tag);
	}

	public boolean removeTag(String tag) {
		return tags.remove(tag);
	}

	public boolean addComment(Comment comment) {
		return comments.add(comment);
	}

	public boolean removeComment(Comment comment) {
		return comments.remove(comment);
	}
}
