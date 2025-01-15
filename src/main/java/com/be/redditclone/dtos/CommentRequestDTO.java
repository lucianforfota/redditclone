package com.be.redditclone.dtos;

public class CommentRequestDTO {

    private String text;
    private Long postId;


    public CommentRequestDTO(String text, Long postId) {
        this.text = text;
        this.postId = postId;
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


}
