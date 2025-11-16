package com.example.blog.web;

import com.example.blog.comment.CommentService;
import com.example.blog.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments/{id}/delete")
    public String delete(@PathVariable Long id, @AuthenticationPrincipal User currentUser,
                         @RequestParam("postId") Long postId) {
        commentService.delete(id, currentUser);
        return "redirect:/posts/view/" + postId;
    }
}
