package com.despedaros4k.forum_world.repositories;

import com.despedaros4k.forum_world.entities.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {
}
