package xyz.goenitz.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.goenitz.blog.model.Post;
import xyz.goenitz.blog.repository.PostRepository;

import java.time.Instant;
import java.util.List;

@Service
public class PostManageService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> getPostList() {
        return postRepository.findAll();
    }

    public void createPost(Post post) {
        post.setCreated(Instant.now());
        post.setUpdated(Instant.now());
        postRepository.save(post);
    }

    public Post getPost(String id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found."));
    }

    public void updatePost(Post post) {
        Post old = this.getPost(post.getId());
        post.setCreated(old.getCreated());
        post.setUpdated(Instant.now());
        postRepository.save(post);
    }

    public void deletePost(String id) {
        postRepository.deleteById(id);
    }
}
