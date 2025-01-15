package com.be.redditclone.repository;

import com.be.redditclone.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllByPost_Id(Long postId);
    List<Comment> findAllByUser_Id(Long postId);
}
