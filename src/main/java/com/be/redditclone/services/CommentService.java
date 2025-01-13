package com.be.redditclone.services;

import com.be.redditclone.config.ResourceNotFoundException;
import com.be.redditclone.dtos.CommentRequestDTO;
import com.be.redditclone.model.Comment;
import com.be.redditclone.model.Post;
import com.be.redditclone.model.Subreddit;
import com.be.redditclone.model.User;
import com.be.redditclone.repository.CommentRepository;
import com.be.redditclone.repository.PostRepository;
import com.be.redditclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;

    private EmailService emailService;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository, EmailService emailService) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.emailService = emailService;
    }

    public Comment createComment(CommentRequestDTO commentRequestDTO){
        //cautam dub id user si post in db
        Post post = postRepository.findById(commentRequestDTO.getPostId()).orElseThrow(()->new ResourceNotFoundException("post not found"));
        User user = userRepository.findById(commentRequestDTO.getUserId()).orElseThrow(()->new ResourceNotFoundException("user not found"));
        //cream un commnet
        Comment comment = new Comment();
        //setam atributele din dto
        comment.setText(commentRequestDTO.getText());
        comment.setCreatedAt(LocalDateTime.now());
        //legam commentu-ul de post si user
        comment.setPost(post);
        comment.setUser(user);
        //trimitem mail de notificare la user-ul care a creat postul
        emailService.sendEmail(post.getUser().getEmail(),"Ai primit un comentariu la postarea ta","Ti-a comentat "+user.getUsername());
        //salvam in db
        return commentRepository.save(comment);
    }
}
