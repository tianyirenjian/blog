package xyz.goenitz.blog.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Data
@NoArgsConstructor
public class Link {
    @Id
    private String id;

    @NotEmpty
    private String name, url;

    private long sort = 0;

    private Instant created, updated;
}
