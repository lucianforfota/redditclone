package com.be.redditclone.controllers;


import com.be.redditclone.dtos.VoteRequestDTO;
import com.be.redditclone.model.Comment;
import com.be.redditclone.model.Vote;
import com.be.redditclone.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class VoteController {
    private VoteService voteService;
    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    public ResponseEntity<Vote> createVote(@RequestBody VoteRequestDTO voteRequestDTO){

        Vote savedVote = voteService.createVote(voteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVote);
    }
}
