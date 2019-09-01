package xyz.goenitz.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;

    private String username, password;

    private Instant lastLogin, created, updated;

    public User(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    @Override
    public String toString() {
        return String.format("User{id=%s, username=%s, lastLogin=%s, created=%s, updated=%s}",
                id, username, lastLogin, created, updated);
    }
}
