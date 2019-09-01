package xyz.goenitz.blog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.goenitz.blog.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    public User findByUsername(String username);
}
