package xyz.goenitz.blog.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
public class Post {
    @Id
    private String id;

    private String title, slug, content, description;

    private long views;

    private boolean show = false;
    private boolean markdown = true;

    private List<String> tags;

    private Instant created, updated;
}
