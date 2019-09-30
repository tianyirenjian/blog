package xyz.goenitz.blog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Post extends Model {
    @Id
    private String id;

    @NotEmpty
    private String title, content, description;

    private long views;

    private boolean show = false;
    private boolean markdown = true;

    private List<String> tags;

}
