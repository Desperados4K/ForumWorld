package com.despedaros4k.forum_world.controllers;

import com.despedaros4k.forum_world.entities.Comment;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CommentController.BASE_URL)
public class CommentController {

    public static final String BASE_URL = "/api/v1/comments";

    public ResponseEntity<Resources<Resource<Comment>>> allComments() {
        return null;
    }

    public ResponseEntity<Resource<Comment>> oneComment(Long id) {
        return null;
    }
}
