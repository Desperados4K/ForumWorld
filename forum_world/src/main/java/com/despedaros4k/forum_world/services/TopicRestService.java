package com.despedaros4k.forum_world.services;

import com.despedaros4k.forum_world.controllers.TopicController;
import com.despedaros4k.forum_world.entities.Topic;
import com.despedaros4k.forum_world.repositories.TopicRepository;
import com.despedaros4k.forum_world.resourceAssemblers.TopicResourceAssembler;
import com.despedaros4k.forum_world.util.exceptions.TopicNotFoundException;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public class TopicRestService implements RestService<Topic> {

    private final TopicRepository topicRepository;
    private final TopicResourceAssembler topicResourceAssembler;

    public TopicRestService(TopicRepository topicRepository, TopicResourceAssembler topicResourceAssembler) {
        this.topicRepository = topicRepository;
        this.topicResourceAssembler = topicResourceAssembler;
    }

    @Override
    public Resources<Resource<Topic>> findAll() {
        List<Resource<Topic>> topics = topicRepository
                .findAll()
                .stream()
                .map(topicResourceAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(topics,
                linkTo(methodOn(TopicController.class).allTopics()).withRel(TopicController.BASE_URL));
    }

    @Override
    public Resource<Topic> findById(Long id) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new TopicNotFoundException(id));
        return topicResourceAssembler.toResource(topic);
    }

    @Override
    public Resource<Topic> save(Topic entity) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
