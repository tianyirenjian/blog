package xyz.goenitz.blog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Link extends Model{
    @Id
    private String id;

    @NotEmpty
    private String name, url;

    private long sort = 0;
}
