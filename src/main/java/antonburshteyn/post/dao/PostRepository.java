package antonburshteyn.post.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import antonburshteyn.post.model.Post;

public interface PostRepository extends JpaRepository<Post, String> {
	Stream<Post> findByAuthorIgnoreCase(String author);

	Stream<Post> findByTagsInIgnoreCase(List<String> tags);

	Stream<Post> findByDateCreatedBetween(LocalDate from, LocalDate to);



}
