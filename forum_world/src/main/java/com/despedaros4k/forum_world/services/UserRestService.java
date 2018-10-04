package com.despedaros4k.forum_world.services;

import com.despedaros4k.forum_world.controllers.UserController;
import com.despedaros4k.forum_world.entities.User;
import com.despedaros4k.forum_world.repositories.UserRepository;
import com.despedaros4k.forum_world.resourceAssemblers.UserResourceAssembler;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public class UserRestService implements RestService<User> {
    private UserRepository userRepository;
    private UserResourceAssembler assembler;

    public UserRestService(UserRepository userRepository, UserResourceAssembler assembler) {
        this.userRepository = userRepository;
        this.assembler = assembler;
    }

    @Override
    public Resources<Resource<User>> findAll() {
        List<Resource<User>> users = userRepository
                .findAll()
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(users,
                linkTo(methodOn(UserController.class).all()).withRel("users"));
    }

    @Override
    public Resource<User> findById(Long id) {
        return null;
    }

    @Override
    public Resource<User> save(User entity) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
