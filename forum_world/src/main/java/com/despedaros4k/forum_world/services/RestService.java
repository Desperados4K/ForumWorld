package com.despedaros4k.forum_world.services;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

public interface RestService<T> {

    Resources<Resource<T>> findAll();
    Resource<T> findById(Long id);
    Resource<T> save(T entity);
    ResponseEntity deleteById(Long id);
    Resource<T> update(T entity, Long id);
}
