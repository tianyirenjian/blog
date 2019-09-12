package xyz.goenitz.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.goenitz.blog.model.Post;
import xyz.goenitz.blog.repository.PostRepository;

import java.time.Instant;

@Service
public class PostManageService {
    @Autowired
    private PostRepository postRepository;

    public Page<Post> getPostList(int page, String keyword) {
        int size = 10;
        Pageable pageable = PageRequest.of(page - 1, size);
        if (keyword == null) {
            return postRepository.findAll(pageable);
        }
        return postRepository.findPostsByRegexpTitle(keyword, pageable);
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
