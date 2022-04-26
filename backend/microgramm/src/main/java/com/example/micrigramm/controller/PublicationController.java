package com.example.micrigramm.controller;

import com.example.micrigramm.entity.Publication;
import com.example.micrigramm.service.PublicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/publication") //http://localhost:8080/publication
public class PublicationController {
    private final PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }
//
//    @GetMapping("/{userId}") //http://localhost:8080/publication/1
//    public List<Publication> getPublicationUser(@PathVariable Long userId){
//        return publicationService.getUserPubs(userId);
//    }


}
