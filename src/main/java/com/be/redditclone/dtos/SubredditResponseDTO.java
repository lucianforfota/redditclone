package com.be.redditclone.dtos;

import java.time.LocalDateTime;

public class SubredditResponseDTO {

    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Integer numberOfPosts;

    public SubredditResponseDTO(String name, String description, LocalDateTime createdAt, Integer numberOfPosts) {
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.numberOfPosts = numberOfPosts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getNumberOfPosts() {
        return numberOfPosts;
    }

    public void setNumberOfPosts(Integer numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }
}
