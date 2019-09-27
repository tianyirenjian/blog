package xyz.goenitz.blog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.goenitz.blog.model.Link;

public interface LinkRepository extends MongoRepository<Link, String> {
}
