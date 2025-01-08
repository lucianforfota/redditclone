package com.be.redditclone.controllers;


import com.be.redditclone.dtos.VoteRequestDTO;
import com.be.redditclone.model.Vote;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class VoteController {

    public ResponseEntity<Vote> createVote(@RequestBody VoteRequestDTO voteRequestDTO){

        return null;
    }
}
