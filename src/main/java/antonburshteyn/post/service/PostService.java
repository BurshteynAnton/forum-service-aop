package antonburshteyn.post.service;

import java.util.List;

import antonburshteyn.post.dto.DatePeriodDto;
import antonburshteyn.post.dto.NewCommentDto;
import antonburshteyn.post.dto.NewPostDto;
import antonburshteyn.post.dto.PostDto;

public interface PostService {
	PostDto addNewPost(String author, NewPostDto newPostDto);

	PostDto findPostById(String id);

	PostDto removePost(String id);

	PostDto updatePost(String id, NewPostDto newPostDto);

	PostDto addComment(String id, String author, NewCommentDto newCommentDto);

	void addLike(String id);

	Iterable<PostDto> findPostsByAuthor(String author);

	Iterable<PostDto> findPostsByTags(List<String> tags);

	Iterable<PostDto> findPostsByPeriod(DatePeriodDto datePeriodDto);

}
