package com.be.redditclone.controllers;


import com.be.redditclone.dtos.CommentRequestDTO;
import com.be.redditclone.dtos.CommentResponseDTO;
import com.be.redditclone.dtos.SubredditResponseDTO;
import com.be.redditclone.model.Comment;
import com.be.redditclone.model.Post;
import com.be.redditclone.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody CommentRequestDTO commentRequestDTO){
        Comment saveComment = commentService.createComment(commentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveComment);
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
