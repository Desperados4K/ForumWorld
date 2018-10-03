package com.despedaros4k.forum_world.repositories;

import com.despedaros4k.forum_world.entities.CommentRating;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommentRatingRepository extends CrudRepository<CommentRating, Long> {
    Optional<CommentRating> findByCommentId(Long commentId);
}
