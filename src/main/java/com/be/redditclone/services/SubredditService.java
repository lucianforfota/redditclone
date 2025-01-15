package com.be.redditclone.services;

import com.be.redditclone.config.ResourceNotFoundException;
import com.be.redditclone.dtos.SubredditRequestDTO;
import com.be.redditclone.dtos.SubredditResponseDTO;
import com.be.redditclone.model.Subreddit;
import com.be.redditclone.repository.SubredditRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubredditService {

    private SubredditRepository subredditRepository;

    @Autowired
    public SubredditService(SubredditRepository subredditRepository) {
        this.subredditRepository = subredditRepository;
    }
    public Subreddit createSubreddit (SubredditRequestDTO subredditRequestDTO){
        return subredditRepository.save(mapFromSubredditRequestDTOtoSubreddit(subredditRequestDTO));
    }

    public Subreddit mapFromSubredditRequestDTOtoSubreddit(SubredditRequestDTO subredditRequestDTO){
        Subreddit subreddit = new Subreddit();
        subreddit.setDescription(subredditRequestDTO.getDescription());
        subreddit.setName(subredditRequestDTO.getName());
        subreddit.setCreatedDate(LocalDateTime.now());
        return subreddit;
    }

    @Transactional
    public List<SubredditResponseDTO> findAllSubreddits(){
        //varianta clasica , nemsechera
//        List<Subreddit> allSubreddits = subredditRepository.findAll();
//        List<SubredditResponseDTO> result = new ArrayList<>();
//        for(Subreddit subreddit:allSubreddits){
//            //bagam in lista un subredditresponsedto obtinut cu datele de la subredditul curent
//            result.add(mapFromSubredditToSubredditResponseDTO(subreddit));
//
//        }
//        return result;

        //varianta pentru vitiori programatori, smechera
        return subredditRepository.findAll().stream()
                .map(subreddit -> mapFromSubredditToSubredditResponseDTO(subreddit))
                .collect(Collectors.toList());

    }

    @Transactional
    public SubredditResponseDTO findSubredditById(Long id){
        Subreddit subreddit = subredditRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("subreddit not found"));;
        return mapFromSubredditToSubredditResponseDTO(subreddit);
    }

    public SubredditResponseDTO mapFromSubredditToSubredditResponseDTO(Subreddit subreddit){
        SubredditResponseDTO subredditResponseDTO = new SubredditResponseDTO();
        subredditResponseDTO.setName(subreddit.getName());
        subredditResponseDTO.setDescription(subreddit.getDescription());
        subredditResponseDTO.setDescription(subreddit.getDescription());
        subredditResponseDTO.setCreatedAt(subreddit.getCreatedDate());
        subredditResponseDTO.setNumberOfPosts(subreddit.getPosts().size());
        return subredditResponseDTO;
    }
}
