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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Value("${user.default.username}")
    private String username;
    @Value("${user.default.password}")
    private String password;

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user;
        List<User> users = userRepository.findAll();
        if (0 == users.size()) {
            password = new BCryptPasswordEncoder().encode(password);
            userRepository.save(new User(username, password));
            users = userRepository.findAll();
        }
        users.forEach(System.out::println);
    }
}
