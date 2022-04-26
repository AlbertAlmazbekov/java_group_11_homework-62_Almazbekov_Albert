package com.example.micrigramm.controller;

import com.example.micrigramm.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://127/.0.0.1:5500", maxAge = 36000)
@RestController
@RequestMapping("/comments") //http://localhost:8080/comment
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

//    @GetMapping("/{commentId}") //http://localhost:8080/comment/{comment_id]
//    public Comment getComment(@PathVariable String commentId) {
//        return commentService.addComment();
//    }
}
