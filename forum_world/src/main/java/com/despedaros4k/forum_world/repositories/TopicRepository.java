package com.despedaros4k.forum_world.repositories;

import com.despedaros4k.forum_world.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
