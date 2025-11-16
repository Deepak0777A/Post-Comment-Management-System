package com.example.blog.web;

import com.example.blog.post.Post;
import com.example.blog.post.PostService;
import com.example.blog.comment.CommentService;
import com.example.blog.comment.CommentForm;
import com.example.blog.post.PostForm;
import com.example.blog.user.User;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final PostService postService;
    private final CommentService commentService;
    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping({"/", "/posts"})
    public String list(@RequestParam(value = "q", required = false) String q,
                       @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "5") int size,
                       Model model) {
        Page<Post> posts = postService.list(q, page, size);
        model.addAttribute("posts", posts);
        model.addAttribute("q", q == null ? "" : q);
        return "posts";
    }

    @GetMapping("/posts/view/{id}")
    public String view(@PathVariable Long id,
                       @AuthenticationPrincipal User currentUser,
                       Model model) {
        Post post = postService.getById(id);
        model.addAttribute("post", post);
        model.addAttribute("comments", commentService.listForPost(id));
        model.addAttribute("commentForm", new CommentForm());
        model.addAttribute("currentUser", currentUser);
        return "post_view";
    }

    @PostMapping("/posts/view/{id}/comments")
    public String addComment(@PathVariable Long id,
                             @AuthenticationPrincipal User currentUser,
                             @Valid @ModelAttribute("commentForm") CommentForm form,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            Post post = postService.getById(id);
            model.addAttribute("post", post);
            model.addAttribute("comments", commentService.listForPost(id));
            model.addAttribute("currentUser", currentUser);
            return "post_view";
        }
        commentService.add(id, currentUser, form.getContent().trim());
        return "redirect:/posts/view/" + id;
    }

    @GetMapping("/posts/new")
    public String newPostForm(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "post_form";
    }

    @PostMapping("/posts")
    public String create(@AuthenticationPrincipal User currentUser,
                         @Valid @ModelAttribute("postForm") PostForm form,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "post_form";
        }
        Post post = postService.create(currentUser, form.getTitle().trim(), form.getContent().trim());
        return "redirect:/posts/view/" + post.getId();
    }

    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable Long id,
                           @AuthenticationPrincipal User currentUser,
                           Model model) {
        Post post = postService.getById(id);
        if (!post.getOwner().getId().equals(currentUser.getId())) {
            return "redirect:/posts/view/" + id;
        }
        PostForm form = new PostForm();
        form.setTitle(post.getTitle());
        form.setContent(post.getContent());
        model.addAttribute("postForm", form);
        model.addAttribute("postId", id);
        return "post_form";
    }

    @PostMapping("/posts/{id}/edit")
    public String update(@PathVariable Long id,
                         @AuthenticationPrincipal User currentUser,
                         @Valid @ModelAttribute("postForm") PostForm form,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("postId", id);
            return "post_form";
        }
        postService.update(currentUser, id, form.getTitle().trim(), form.getContent().trim());
        return "redirect:/posts/view/" + id;
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable Long id, @AuthenticationPrincipal User currentUser) {
        postService.delete(currentUser, id);
        return "redirect:/posts";
    }
}