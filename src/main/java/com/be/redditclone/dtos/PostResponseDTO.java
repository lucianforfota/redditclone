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
}
