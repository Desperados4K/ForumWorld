package com.despedaros4k.forum_world;

import com.despedaros4k.forum_world.entities.enums.Gender;
import com.despedaros4k.forum_world.entities.User;
import com.despedaros4k.forum_world.entities.enums.Role;
import com.despedaros4k.forum_world.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByNameUser_thenReturnUser() {
        // given
        User user = User.builder().userName("userName").firstName("userFirstname").lastName("userLastname").gender(Gender.FEMALE).email("user@gmail.com").role(Role.MODERATOR).password("password").authorized(false).build();
        entityManager.persist(user);
        entityManager.flush();

        // when
        Optional<User> found = userRepository.findByUserName(user.getUserName());

        // then
        assertThat(found.get().getUserName())
                .isEqualTo(user.getUserName());
    }






}
