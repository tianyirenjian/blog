package xyz.goenitz.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import xyz.goenitz.blog.model.User;
import xyz.goenitz.blog.repository.UserRepository;

import java.util.List;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Value("${user.default.username:username}")
    private String username;
    @Value("${user.default.password:password}")
    private String password;

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<User> users = userRepository.findAll();
        if (0 == users.size()) {
            password = new BCryptPasswordEncoder().encode(password);
            userRepository.save(new User(username, password));
            users = userRepository.findAll();
        }
        users.forEach(System.out::println);
    }
}
