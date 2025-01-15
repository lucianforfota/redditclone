package com.be.redditclone.services;

import com.be.redditclone.config.ResourceNotFoundException;
import com.be.redditclone.dtos.VoteRequestDTO;
import com.be.redditclone.model.Post;
import com.be.redditclone.model.User;
import com.be.redditclone.model.Vote;
import com.be.redditclone.model.VoteType;
import com.be.redditclone.repository.PostRepository;
import com.be.redditclone.repository.UserRepository;
import com.be.redditclone.repository.VoteRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteService {

    private VoteRepository voteRepository;

    private PostRepository postRepository;
    private UserRepository userRepository;

    @Transactional
    public Vote createVote(VoteRequestDTO voteRequestDTO) {
        Post post = postRepository.findById(voteRequestDTO.getPostId()).orElseThrow(() -> new ResourceNotFoundException("post not found"));
        String usernameLoggedIn = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userRepository.findByUsername(usernameLoggedIn).orElseThrow(()->new ResourceNotFoundException("user not found"));
        Optional<Vote> voteOptional = voteRepository.findByUser_IdAndPost_Id(user.getId(), voteRequestDTO.getPostId());
        //daca user-ul a votat deja
        if (voteOptional.isPresent()) {
            return handleExistingVote(voteOptional.get(), voteRequestDTO, post);
        } else {
            return createNewVote(voteRequestDTO, post, user);
        }
    }

    @Transactional
    private Vote handleExistingVote(Vote existingVote, VoteRequestDTO voteRequestDTO, Post post) {
        // If the user attempts to vote with the same vote type, cancel the vote
        if (existingVote.getVoteType().equals(voteRequestDTO.getVoteType())) {
            voteRepository.delete(existingVote);
            post.setVoteCount(post.getVoteCount() + voteRequestDTO.getVoteType().getValue());
            postRepository.save(post);
            return null;
        } else {
            throw new ResourceNotFoundException("You can only cancel your vote. You have already voted.");
        }
    }

    @Transactional
    private Vote createNewVote(VoteRequestDTO voteRequestDTO, Post post, User user) {
        // Create and save a new vote
        Vote newVote = new Vote();
        newVote.setVoteType(voteRequestDTO.getVoteType());
        newVote.setPost(post);
        newVote.setUser(user);

        post.setVoteCount(post.getVoteCount() + voteRequestDTO.getVoteType().getValue());
        postRepository.save(post);

        return voteRepository.save(newVote);
    }

}
