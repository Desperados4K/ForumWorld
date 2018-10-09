package com.despedaros4k.forum_world.services;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

public interface RestService<T> {

    Resources<Resource<T>> findAll();
    Resource<T> findById(Long id);
    Resource<T> save(T entity);
    void deleteById(Long id);
    Resource<T> update(T entity, Long id);
}
