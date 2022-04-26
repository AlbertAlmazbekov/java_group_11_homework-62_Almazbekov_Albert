package com.example.micrigramm.repository;

import com.example.micrigramm.entity.Publication;
import com.example.micrigramm.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PublicationRepository extends PagingAndSortingRepository<Publication, Long> {
    Page<Publication> findAllByAuthor(User author, Pageable pageable);

    @Query("SELECT p FROM Publication p " +
            "INNER JOIN Subscribe s ON p.author = s.subscription " +
            "WHERE s.subscriber = :user " +
            "ORDER BY p.dateAdded DESC")
    List<Publication> getFeed(User user);
}
