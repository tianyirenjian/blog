package xyz.goenitz.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.goenitz.blog.model.Post;
import xyz.goenitz.blog.repository.PostRepository;

import java.util.List;

@Service
public class PostManageService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> getPostList() {
        return postRepository.findAll();
    }

    public void createPost(Post post) {
        postRepository.save(post);
    }
}
