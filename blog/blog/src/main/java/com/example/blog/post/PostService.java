package com.example.blog.post;

import com.example.blog.user.User;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional(readOnly = true)
    public Page<Post> list(String query, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        if (query == null || query.isBlank()) {
            return postRepository.findAllByOrderByCreatedAtDesc(pageable);
        }
        return postRepository.search(query.trim(), pageable);
    }

    @Transactional(readOnly = true)
    public Post getById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }

    @Transactional
    public Post create(User owner, String title, String content) {
        Post post = new Post(title, content, owner);
        return postRepository.save(post);
    }

    @Transactional
    public void update(User currentUser, Long postId, String title, String content) {
        Post post = getById(postId);
        if (!post.getOwner().getId().equals(currentUser.getId())) {
            throw new IllegalArgumentException("Not authorized");
        }
        post.setTitle(title);
        post.setContent(content);
    }

    @Transactional
    public void delete(User currentUser, Long postId) {
        Post post = getById(postId);
        if (!post.getOwner().getId().equals(currentUser.getId())) {
            throw new IllegalArgumentException("Not authorized");
        }
        postRepository.delete(post);
    }
}