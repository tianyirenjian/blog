package xyz.goenitz.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import xyz.goenitz.blog.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {
    @Query("{'title': {$regex: ?0}}")
    public Page<Post> findPostsByRegexpTitle(String regexp, Pageable pageable);
}
