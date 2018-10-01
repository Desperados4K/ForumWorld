package com.despedaros4k.forum_world.repositories;

import com.despedaros4k.forum_world.entities.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
