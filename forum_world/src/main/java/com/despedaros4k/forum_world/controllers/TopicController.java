package com.despedaros4k.forum_world.controllers;

import com.despedaros4k.forum_world.entities.Topic;
import com.despedaros4k.forum_world.services.TopicRestService;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TopicController.BASE_URL)
public class TopicController {

    public static final String BASE_URL = "/api/v1/topics";

    private final TopicRestService topicRestService;

    public TopicController(TopicRestService topicRestService) {
        this.topicRestService = topicRestService;
    }

    @GetMapping(produces = "application/hal+json")
    public ResponseEntity<Resources<Resource<Topic>>> allTopics() {
        return ResponseEntity.ok(topicRestService.findAll());
    }

    public ResponseEntity<Resource<Topic>> oneTopic(Long id) {
        return null;
    }
}
