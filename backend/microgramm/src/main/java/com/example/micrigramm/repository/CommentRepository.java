package com.example.micrigramm.repository;

import com.example.micrigramm.entity.Comment;
import com.example.micrigramm.entity.Publication;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, String> {
    List<Comment> findAllByPublicationOrderByDateAddedDesc(Publication publication);
}
