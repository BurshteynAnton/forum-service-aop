package antonburshteyn.post.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "user", "dateCreated"})
@Entity
@Table(name = "comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	private String user;

	@Setter
	private String message;

	@Column(name = "date_created")
	private LocalDateTime dateCreated = LocalDateTime.now();

	private int likes;

	public Comment(String user, String message) {
		this.user = user;
		this.message = message;
	}

	public void addLike() {
		likes++;
	}
}