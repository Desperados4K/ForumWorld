package com.despedaros4k.forum_world.controllers;

import com.despedaros4k.forum_world.entities.Comment;
import com.despedaros4k.forum_world.services.CommentRestService;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CommentController.BASE_URL)
public class CommentController {

    public static final String BASE_URL = "/api/v1/comments";

    private final CommentRestService commentRestService;

    public CommentController(CommentRestService commentRestService) {
        this.commentRestService = commentRestService;
    }

    @GetMapping(produces = "application/hal+json")
    public ResponseEntity<Resources<Resource<Comment>>> allComments() {
        return ResponseEntity.ok(commentRestService.findAll());
    }

    public ResponseEntity<Resource<Comment>> oneComment(Long id) {
        return null;
    }
}
