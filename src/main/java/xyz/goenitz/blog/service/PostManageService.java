package xyz.goenitz.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.stereotype.Service;
import xyz.goenitz.blog.model.Post;
import xyz.goenitz.blog.repository.PostRepository;

import java.time.Instant;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

@Service
public class PostManageService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Page<Post> getPostList(int page, String keyword, String tag) {
        int size = 10;
        Pageable pageable = PageRequest.of(page - 1, size);
        if (keyword != null) {
            return postRepository.findPostsByRegexpTitle(keyword, pageable);
        }
        if (tag != null) {
            return postRepository.findByTags(tag, pageable);
        }
        return postRepository.findAll(pageable);
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

    public List<Object> getAllTags() {
        UnwindOperation unwindOperation = unwind("tags");
        GroupOperation groupOperation = group("tags").count().as("count");
        Aggregation aggregation = Aggregation.newAggregation(unwindOperation, groupOperation);

        AggregationResults<Object> results = mongoTemplate.aggregate(aggregation, Post.class, Object.class);
        return results.getMappedResults();
    }
}
