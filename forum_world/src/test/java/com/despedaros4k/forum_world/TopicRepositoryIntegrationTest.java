package com.despedaros4k.forum_world;

import com.despedaros4k.forum_world.entities.Topic;
import com.despedaros4k.forum_world.entities.User;
import com.despedaros4k.forum_world.entities.enums.Category;
import com.despedaros4k.forum_world.entities.enums.Gender;
import com.despedaros4k.forum_world.entities.enums.Role;
import com.despedaros4k.forum_world.repositories.TopicRepository;
import com.despedaros4k.forum_world.repositories.UserRepository;
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
public class TopicRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TopicRepository topicRepository;

    @Test
    public void whenFindByTopicTitle_thenReturnTopic() {
        // given

        Topic topic = Topic.builder().title("title").category(Category.SPORT).date(LocalDateTime.now()).build();
        entityManager.persist(topic);
        entityManager.flush();

        // when
        Optional<Topic> found = topicRepository.findByTitle(topic.getTitle());

        // then
        assertThat(found.get().getTitle())
                .isEqualTo(topic.getTitle());
    }
}
