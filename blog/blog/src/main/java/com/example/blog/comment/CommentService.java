package com.example.blog.comment;

import com.example.blog.post.Post;
import com.example.blog.post.PostService;
import com.example.blog.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, PostService postService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
    }

    @Transactional(readOnly = true)
    public List<Comment> listForPost(Long postId) {
        Post post = postService.getById(postId);
        return commentRepository.findByPostOrderByCreatedAtAsc(post);
    }

    @Transactional
    public void add(Long postId, User author, String content) {
        Post post = postService.getById(postId);
        Comment comment = new Comment(post, author, content);
        commentRepository.save(comment);
    }

    @Transactional
    public void delete(Long commentId, User currentUser) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        if (!comment.getAuthor().getId().equals(currentUser.getId())) {
            throw new IllegalArgumentException("Not authorized");
        }
        commentRepository.delete(comment);
    }
}
