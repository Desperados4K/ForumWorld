package com.despedaros4k.forum_world.repositories;

import com.despedaros4k.forum_world.entities.EntryRating;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EntryRatingRepository extends CrudRepository<EntryRating, Long> {
    Optional<EntryRating> findByEntryId(Long entryId);
}
