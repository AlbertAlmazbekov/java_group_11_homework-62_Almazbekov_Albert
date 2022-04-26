package com.example.micrigramm.service;

import com.example.micrigramm.entity.Comment;
import com.example.micrigramm.entity.Publication;
import com.example.micrigramm.entity.User;
import com.example.micrigramm.repository.CommentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void addComment (Integer id, String commentText, User author, Publication publication){
    Comment c = new Comment(commentText, LocalDateTime.now(), author, publication);
    commentRepository.save(c);
    }

    public void deleteComment(Integer id, String commentText, User author, Publication publication){
        Comment deleteComment = new Comment(commentText, LocalDateTime.now(), author, publication);
        commentRepository.save(deleteComment);
    }
    
}
