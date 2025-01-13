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
        User user = userRepository.findById(voteRequestDTO.getUserId()).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        Optional<Vote> voteOptional = voteRepository.findByUser_IdAndPost_Id(voteRequestDTO.getUserId(), voteRequestDTO.getPostId());
        //daca user-ul a votat deja
        if (voteOptional.isPresent()) {
            Vote existingVote = voteOptional.get();
            //daca user-ul vrea sa voeteze cu acelasi vot ca cel dat deja
            if (existingVote.getVoteType().equals(voteRequestDTO.getVoteType())) {
                voteRepository.delete(existingVote);
                //sterg votul
                if (voteRequestDTO.getVoteType().equals(VoteType.UP_VOTE)) {
                    post.setVoteCount(post.getVoteCount() - 1);
                } else {
                    post.setVoteCount(post.getVoteCount() + 1);
                }
                //updatez vote count-ul postului de la care sterg votul
                postRepository.save(post);
            } else {
                //aletfel dau exceptie
                throw new ResourceNotFoundException("nu poti decat sa anulezi votul. Ai votat deja");
            }
            return null;

        } else {
            //altfel
            // fac un vot nou
            Vote vote = new Vote();
            // ii setez tipul la cel care vine din dto
            vote.setVoteType(voteRequestDTO.getVoteType());
            //il leg de post si user
            vote.setPost(post);
            vote.setUser(user);
            //salvez votul

            if (voteRequestDTO.getVoteType().equals(VoteType.UP_VOTE)) {
                post.setVoteCount(post.getVoteCount() - 1);
            } else {
                post.setVoteCount(post.getVoteCount() + 1);
            }
            postRepository.save(post);
            return voteRepository.save(vote);
            //update votecountul postului la care adaug noul vot
        }
    }
}
