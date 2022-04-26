package com.example.micrigramm.service;

import com.example.micrigramm.entity.Like;
import com.example.micrigramm.entity.Publication;
import com.example.micrigramm.entity.User;
import com.example.micrigramm.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LikeService {
    private final LikeRepository likerepository;

    public LikeService(LikeRepository likerepository) {
        this.likerepository = likerepository;
    }

    public void addLike (Integer id, User likeOwner, Publication publication){
        Like l =  new Like(likeOwner, publication,LocalDateTime.now());
        likerepository.save(l);
    }
}
