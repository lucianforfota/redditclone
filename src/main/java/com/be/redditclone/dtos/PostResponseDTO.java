package com.be.redditclone.dtos;

public class PostResponseDTO {

    private Long id;
    private String title;
    private String text;
    private String username;
    private String subredditName;
    private Integer voteCount;
    private Integer commentCount;
    private Long duration;
    private boolean isUpVoted;
    private boolean isDownVoted;

    public PostResponseDTO(){
    }

    public PostResponseDTO(Long id, String title, String text, String username, String subredditName, Integer voteCount, Integer commentCount, Long duration, boolean isUpVoted, boolean isDownVoted) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.username = username;
        this.subredditName = subredditName;
        this.voteCount = voteCount;
        this.commentCount = commentCount;
        this.duration = duration;
        this.isUpVoted = isUpVoted;
        this.isDownVoted = isDownVoted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubredditName() {
        return subredditName;
    }

    public void setSubredditName(String subredditName) {
        this.subredditName = subredditName;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public boolean isUpVoted() {
        return isUpVoted;
    }

    public void setUpVoted(boolean upVoted) {
        isUpVoted = upVoted;
    }

    public boolean isDownVoted() {
        return isDownVoted;
    }

    public void setDownVoted(boolean downVoted) {
        isDownVoted = downVoted;
    }
}
