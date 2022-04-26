package com.example.micrigramm.repository;

import com.example.micrigramm.entity.Like;
import com.example.micrigramm.entity.Publication;
import com.example.micrigramm.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LikeRepository extends CrudRepository<Like, String> {

    List<Like> findAllByPublicationOrderByDateAddedDesc(Publication publication);

//    int countLikeByLikeOwnerLike(User likeOwner);

    int countAllByPublicationId(Long publication_id);
    // select count(id) from Like where publication.id = publication_id
}
