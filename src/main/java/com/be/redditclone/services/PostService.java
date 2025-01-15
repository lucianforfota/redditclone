package com.be.redditclone.services;

import com.be.redditclone.config.ResourceNotFoundException;
import com.be.redditclone.dtos.PostRequestDTO;
import com.be.redditclone.dtos.PostResponseDTO;
import com.be.redditclone.model.*;
import com.be.redditclone.repository.PostRepository;
import com.be.redditclone.repository.SubredditRepository;

import com.be.redditclone.repository.UserRepository;
import com.be.redditclone.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {


    private PostRepository postRepository;
    private SubredditRepository subredditRepository;

    private UserRepository userRepository;

    private VoteRepository voteRepository;

    private UserService userService;

    @Autowired
    public PostService(PostRepository postRepository, SubredditRepository subredditRepository, UserRepository userRepository, UserService userService, VoteRepository voteRepository) {
        this.postRepository = postRepository;
        this.subredditRepository = subredditRepository;
        this.userRepository=userRepository;
        this.voteRepository=voteRepository;
        this.userService= userService;
    }

    public Post createPost(PostRequestDTO postRequestDTO){
        //gasim subreddit-ul si user-ul dupa id-ul din dto din baza de date
        Subreddit subreddit = subredditRepository.findById(postRequestDTO.getSubredditId()).orElseThrow(()->new ResourceNotFoundException("subreddit not found"));
        String usernameLoggedIn = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userRepository.findByUsername(usernameLoggedIn).orElseThrow(()->new ResourceNotFoundException("user not found"));
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

    public List<PostResponseDTO> findAllPosts (){
//        List<Post> allPosts = postRepository.findAll();
//
//        List<PostResponseDTO> result = new ArrayList<>();
//
//        for (Post post: allPosts) {
//            result.add(mapFromPostToPostReponseDTO(post));
//        }
//        return result;

        //varianta de smecheri
        return postRepository.findAll().stream()
                .map(post->mapFromPostToPostReponseDTO(post))
                .collect(Collectors.toList());
    }

    public List<PostResponseDTO> findAllPostsBySubredditId(Long id){
        return postRepository.findAllBySubrredit_Id(id).stream()
                .map(post->mapFromPostToPostReponseDTO(post))
                .collect(Collectors.toList());
    }

    public List<PostResponseDTO> findAllPostsByUserId(Long id){
        return postRepository.findAllByUser_Id(id).stream()
                .map(post->mapFromPostToPostReponseDTO(post))
                .collect(Collectors.toList());
    }

    public PostResponseDTO findPostById(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post not found"));
        return mapFromPostToPostReponseDTO(post);
    }

    public PostResponseDTO mapFromPostToPostReponseDTO(Post post){
        PostResponseDTO postResponseDTO = new PostResponseDTO();


        User user = userService.findLoggedInUser();
        postResponseDTO.setText(post.getText());
        postResponseDTO.setTitle(post.getTitle());
        postResponseDTO.setCommentCount(post.getComments().size());
        postResponseDTO.setDuration(Duration.between(post.getCreatedAt(), LocalDateTime.now()).toHours());
        postResponseDTO.setSubredditName(post.getSubreddit().getName());
        postResponseDTO.setUsername(post.getUser().getUsername());
        Optional<Vote> voteOptional = voteRepository.findByUser_IdAndPost_Id(user.getId(), post.getId());
        if (voteOptional.isEmpty()){
            postResponseDTO.setUpVoted(false);
            postResponseDTO.setDownVoted(false);
        } else if (voteOptional.get().getVoteType().equals(VoteType.UP_VOTE)){
            postResponseDTO.setUpVoted(false);
            postResponseDTO.setDownVoted(true);
        } else {
            postResponseDTO.setUpVoted(true);
            postResponseDTO.setDownVoted(false);
        }
        return postResponseDTO;
    }


}
