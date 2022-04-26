package com.example.micrigramm.service;

import com.example.micrigramm.dto.PublicationDTO;
import com.example.micrigramm.entity.Publication;
import com.example.micrigramm.entity.Subscribe;
import com.example.micrigramm.entity.User;
import com.example.micrigramm.repository.CommentRepository;
import com.example.micrigramm.repository.LikeRepository;
import com.example.micrigramm.repository.PublicationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PublicationService {
    private final PublicationRepository publicationRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;

    public PublicationService(PublicationRepository publicationRepository, LikeRepository likeRepository, CommentRepository commentRepository) {
        this.publicationRepository = publicationRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
    }

    public void addPublications(String picture, User author) {
        Publication p = new Publication(picture, author, LocalDateTime.now());
        publicationRepository.save(p);
    }

    public void addSubscribe(User user, User user2) {
        Subscribe subscribe = new Subscribe(user, user2, LocalDateTime.now());
        // 1 tariel jandos 2022.09.01
    }



    public PublicationDTO createPublicationDto(Publication p) {
        var likes = likeRepository.findAllByPublicationOrderByDateAddedDesc(p);
        var comments = commentRepository.findAllByPublicationOrderByDateAddedDesc(p);
        return PublicationDTO.from(p, likes, comments);
    }
}
