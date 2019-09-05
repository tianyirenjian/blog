package xyz.goenitz.blog.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@NoArgsConstructor
public class Post {
    @Id
    private String id;

    @NotEmpty
    private String title, content, description;

    private String slug;

    private long views;

    private boolean show = false;
    private boolean markdown = true;

    private List<String> tags;

    private Instant created, updated;
}
