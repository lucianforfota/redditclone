package com.be.redditclone.controllers;

import com.be.redditclone.dtos.SubredditRequestDTO;
import com.be.redditclone.dtos.SubredditResponseDTO;
import com.be.redditclone.model.Subreddit;
import com.be.redditclone.services.SubredditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subreddit")
public class SubredditController {


    private SubredditService subredditService;

    @Autowired
    public SubredditController(SubredditService subredditService) {
        this.subredditService = subredditService;
    }

    @PostMapping
    public ResponseEntity<Subreddit> createSubreddit(@RequestBody SubredditRequestDTO subredditRequestDTO){
        Subreddit savedSubreddit = subredditService.createSubreddit(subredditRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSubreddit);
    }

    @GetMapping
    public ResponseEntity<List<SubredditResponseDTO>> findAllSubreddits(){
        return ResponseEntity.ok(subredditService.findAllSubreddits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubredditResponseDTO> findSubredditById(@PathVariable Long id){
        return ResponseEntity.ok(subredditService.findSubredditById(id));
    }

}
