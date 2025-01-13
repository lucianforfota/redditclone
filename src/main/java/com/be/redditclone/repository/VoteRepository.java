package com.be.redditclone.repository;

import com.be.redditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote,Long> {

    public Optional<Vote> findByUser_IdAndPost_Id(Long userId, Long postId);
}
