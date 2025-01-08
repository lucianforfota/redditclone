package com.be.redditclone.services;

import com.be.redditclone.dtos.SubredditRequestDTO;
import com.be.redditclone.model.Subreddit;
import com.be.redditclone.repository.SubredditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SubredditService {

    private SubredditRepository subredditRepository;

    @Autowired
    public SubredditService(SubredditRepository subredditRepository) {
        this.subredditRepository = subredditRepository;
    }
    public Subreddit createSubreddit (SubredditRequestDTO subredditRequestDTO){
        return subredditRepository.save(mapFromSubredditRequestDTOtoSubreddit(subredditRequestDTO));
    }

    public Subreddit mapFromSubredditRequestDTOtoSubreddit(SubredditRequestDTO subredditRequestDTO){
        Subreddit subreddit = new Subreddit();
        subreddit.setDescription(subredditRequestDTO.getDescription());
        subreddit.setName(subredditRequestDTO.getName());
        subreddit.setCreatedDate(LocalDateTime.now());
        return subreddit;
    }
}
