package com.be.redditclone.controllers;

import com.be.redditclone.dtos.PostRequestDTO;
import com.be.redditclone.dtos.PostResponseDTO;
import com.be.redditclone.dtos.SubredditResponseDTO;
import com.be.redditclone.model.Post;
import com.be.redditclone.model.Subreddit;
import com.be.redditclone.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {


    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostRequestDTO postRequestDTO){
        Post savedPost = postService.createPost(postRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> findAllPosts(){
        return ResponseEntity.ok(postService.findAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> findPostById(@PathVariable Long id){
        return ResponseEntity.ok(postService.findPostById(id));
    }

    @GetMapping("/subreddit/{id}")
    public ResponseEntity<List<PostResponseDTO>> findPostsBySubredditId(@PathVariable Long id){
        return ResponseEntity.ok(postService.findAllPostsBySubredditId(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<PostResponseDTO>> findPostsByUserId(@PathVariable Long id){
        return ResponseEntity.ok(postService.findAllPostsByUserId(id));
    }
}
