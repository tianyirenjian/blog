package xyz.goenitz.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends Model{
    @Id
    private String id;

    private String username, password;

    private Instant lastLogin;

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
