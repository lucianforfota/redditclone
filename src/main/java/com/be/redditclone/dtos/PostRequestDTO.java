package com.be.redditclone.dtos;

public class PostRequestDTO {

    private String title;

    private String text;

    private Long subredditId;
    private Long userId;

    public PostRequestDTO(String title, String text, Long subredditId, Long userId) {
        this.title = title;
        this.text = text;
        this.subredditId = subredditId;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getSubredditId() {
        return subredditId;
    }

    public void setSubredditId(Long subredditId) {
        this.subredditId = subredditId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
