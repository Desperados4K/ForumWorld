package com.despedaros4k.forum_world.services;

import com.despedaros4k.forum_world.entities.Topic;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

public class TopicRestService implements RestService<Topic> {
    @Override
    public Resources<Resource<Topic>> findAll() {
        return null;
    }

    @Override
    public Resource<Topic> findById(Long id) {
        return null;
    }

    @Override
    public Resource<Topic> save(Topic entity) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
