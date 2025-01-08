package com.be.redditclone.controllers;

import com.be.redditclone.dtos.PostRequestDTO;
import com.be.redditclone.dtos.PostResponseDTO;
import com.be.redditclone.dtos.SubredditResponseDTO;
import com.be.redditclone.model.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostRequestDTO postRequestDTO){
        return null;
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> findAllPosts(){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> findPostById(@PathVariable Long id){
        return null;
    }

    @GetMapping("/subreddit/{id}")
    public ResponseEntity<List<PostResponseDTO>> findPostsBySubredditId(@PathVariable Long id){
        return null;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<PostResponseDTO>> findPostsByUserId(@PathVariable Long id){
        return null;
    }
}
