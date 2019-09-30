package xyz.goenitz.blog.model;

import lombok.Data;

import java.time.Instant;

@Data
public class Model {
    protected Instant created, updated;
}
