package com.despedaros4k.forum_world.controllers;

import com.despedaros4k.forum_world.services.TopicRestService;
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
}
