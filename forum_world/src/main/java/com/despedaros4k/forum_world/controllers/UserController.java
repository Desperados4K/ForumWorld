package com.despedaros4k.forum_world.controllers;

import com.despedaros4k.forum_world.entities.User;
import com.despedaros4k.forum_world.services.UserRestService;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRestService userRestService;

    public UserController(UserRestService userRestService) {
        this.userRestService = userRestService;
    }

    @GetMapping(produces = "application/hal+json")
    public ResponseEntity<Resources<Resource<User>>> all() {
        return ResponseEntity.ok(userRestService.findAll());
    }

}
