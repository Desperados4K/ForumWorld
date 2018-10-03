package com.despedaros4k.forum_world.repositories;

import com.despedaros4k.forum_world.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
