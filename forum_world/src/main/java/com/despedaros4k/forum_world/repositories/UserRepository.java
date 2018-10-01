package com.despedaros4k.forum_world.repositories;

import com.despedaros4k.forum_world.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
