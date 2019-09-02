package xyz.goenitz.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.goenitz.blog.model.Post;
import xyz.goenitz.blog.repository.PostRepository;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @ResponseBody
    @GetMapping
    public List<Post> index() {
        return postRepository.findAll();
    }
}
