package com.be.redditclone.controllers;


import com.be.redditclone.dtos.CommentRequestDTO;
import com.be.redditclone.dtos.CommentResponseDTO;
import com.be.redditclone.dtos.SubredditResponseDTO;
import com.be.redditclone.model.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody CommentRequestDTO commentRequestDTO){
        return null;
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<List<CommentResponseDTO>> findAllCommentsByPostId(@PathVariable Long id){
        return null;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<CommentResponseDTO>> findAllCommentsByUserId(@PathVariable Long id){
        return null;
    }
}
