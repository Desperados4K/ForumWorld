package com.despedaros4k.forum_world.repositories;

import com.despedaros4k.forum_world.entities.Entry;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EntryRepository extends PagingAndSortingRepository<Entry, Long> {
}
