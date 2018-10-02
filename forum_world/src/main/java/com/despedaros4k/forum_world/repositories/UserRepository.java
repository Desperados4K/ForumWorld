package com.despedaros4k.forum_world.repositories;

import com.despedaros4k.forum_world.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUserName(String username);
}
