package com.example.blog.comment;

import com.example.blog.post.Post;
import com.example.blog.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostOrderByCreatedAtAsc(Post post);
    Optional<Comment> findByIdAndAuthor(Long id, User author);
}