package xyz.goenitz.blog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.goenitz.blog.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {
    public Post findBySlug(String slug);
}
