package com.example.blog.comment;

import jakarta.validation.constraints.NotBlank;

public class CommentForm {
    @NotBlank(message = "Comment cannot be empty")
    private String content;

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}