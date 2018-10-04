package com.despedaros4k.forum_world.repositories;

import com.despedaros4k.forum_world.entities.Entry;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface EntryRepository extends PagingAndSortingRepository<Entry, Long> {
    Optional<Entry> findByTitle(String title);
}
