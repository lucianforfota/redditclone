package com.be.redditclone.dtos;

public class CommentRequestDTO {

    private String text;
    private Long postId;
    private Long userId;

    public CommentRequestDTO(String text, Long postId, Long userId) {
        this.text = text;
        this.postId = postId;
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
