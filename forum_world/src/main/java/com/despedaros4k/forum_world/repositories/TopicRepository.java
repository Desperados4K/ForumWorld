package com.despedaros4k.forum_world.repositories;

import com.despedaros4k.forum_world.entities.Topic;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TopicRepository extends PagingAndSortingRepository<Topic, Long> {
}
