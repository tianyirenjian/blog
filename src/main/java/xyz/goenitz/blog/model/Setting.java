package xyz.goenitz.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Setting {
    @Id
    private String id;

    @NotNull
    private String key;

    @NotNull
    private String name, value;
}
