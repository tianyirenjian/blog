package xyz.goenitz.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Setting {
    @Id
    private String id;

    @NotEmpty
    private String webName;
    private String title, keywords, description;
    private Boolean maintenance = false;
    private Boolean showContentInList = false;
    private Integer perPage = 6;
    private String header;
    private String footer;
}
