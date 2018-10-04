package com.despedaros4k.forum_world.repositories;

import com.despedaros4k.forum_world.entities.Topic;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface TopicRepository extends PagingAndSortingRepository<Topic, Long> {
    Optional<Topic> findByTitle(String title);

}
