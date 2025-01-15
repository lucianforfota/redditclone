package com.be.redditclone.repository;

import com.be.redditclone.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAllBySubrredit_Id(Long subredditId);
    List<Post> findAllByUser_Id(Long subredditId);
}
