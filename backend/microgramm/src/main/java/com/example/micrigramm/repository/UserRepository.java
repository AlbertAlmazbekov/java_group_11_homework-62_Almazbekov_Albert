package com.example.micrigramm.repository;

import com.example.micrigramm.entity.Comment;
import com.example.micrigramm.entity.User;
import org.springframework.data.repository.CrudRepository;

import javax.lang.model.element.Name;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByAccountName(String accountName);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByAccountName(String accountName);

    List<User> findByNameContainsIgnoringCase(String name);

//    List<User> findByName(String name);
//    // select * from review where user like '%?0%'
//    List<User> findByLogin(String login);
//    // select * from review where user like '%?0%'
//    List<User> findByEmail(String email);
//    // select * from review where user like '%?0%'



//    Optional<User> findByEmail(String email);
}
