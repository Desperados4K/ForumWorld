package com.despedaros4k.forum_world.resourceAssemblers;

import com.despedaros4k.forum_world.controllers.TopicController;
import com.despedaros4k.forum_world.entities.Topic;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class TopicResourceAssembler implements ResourceAssembler<Topic, Resource<Topic>> {
    @Override
    public Resource<Topic> toResource(Topic topic) {
        return new Resource<>(topic,
                linkTo(methodOn(TopicController.class).oneTopic(topic.getId())).withSelfRel(),
                linkTo(methodOn(TopicController.class).allTopics()).withRel("topics"));
    }
}
