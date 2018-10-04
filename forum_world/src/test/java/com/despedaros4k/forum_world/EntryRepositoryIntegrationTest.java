package com.despedaros4k.forum_world;

import com.despedaros4k.forum_world.entities.Entry;
import com.despedaros4k.forum_world.repositories.EntryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EntryRepositoryIntegrationTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EntryRepository entryRepository;

    @Test
    public void whenFindByTitleEntry_thenReturnEntry() {
        // given
        Entry entry = Entry.builder().title("title").content("content").date(LocalDateTime.now()).build();
        entityManager.persist(entry);
        entityManager.flush();

        // when
        Optional<Entry> found = entryRepository.findByTitle(entry.getTitle());

        // then
        assertThat(found.get().getTitle())
                .isEqualTo(entry.getTitle());
    }
}
