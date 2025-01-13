package com.be.redditclone.services;

import com.be.redditclone.config.ResourceNotFoundException;
import com.be.redditclone.dtos.PostRequestDTO;
import com.be.redditclone.model.Post;
import com.be.redditclone.model.Subreddit;
import com.be.redditclone.model.User;
import com.be.redditclone.repository.PostRepository;
import com.be.redditclone.repository.SubredditRepository;

import com.be.redditclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {


    private PostRepository postRepository;
    private SubredditRepository subredditRepository;

    private UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, SubredditRepository subredditRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.subredditRepository = subredditRepository;
        this.userRepository=userRepository;
    }

    public Post createPost(PostRequestDTO postRequestDTO){
        //gasim subreddit-ul si user-ul dupa id-ul din dto din baza de date
        Subreddit subreddit = subredditRepository.findById(postRequestDTO.getSubredditId()).orElseThrow(()->new ResourceNotFoundException("subreddit not found"));
        User user = userRepository.findById(postRequestDTO.getUserId()).orElseThrow(()->new ResourceNotFoundException("user not found"));
        //cream un post
        Post post = new Post();
        //ii setam toate atributele care vin din dto
        post.setText(postRequestDTO.getText());
        post.setTitle(postRequestDTO.getTitle());
        post.setCreatedAt(LocalDateTime.now());
        //legam postul de subreddit si suer
        post.setSubreddit(subreddit);
        post.setUser(user);
        //salvam postul in baza de date
        return postRepository.save(post);
    }
}
