package com.despedaros4k.forum_world.util;

import com.despedaros4k.forum_world.entities.*;
import com.despedaros4k.forum_world.entities.enums.Category;
import com.despedaros4k.forum_world.entities.enums.Gender;
import com.despedaros4k.forum_world.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
@Slf4j

public class DataLoader {

    private UserRepository userRepository;
    private TopicRepository topicRepository;
    private EntryRepository entryRepository;
    private CommentRepository commentRepository;
    private RoleRepository roleRepository;

    public DataLoader(UserRepository userRepository, TopicRepository topicRepository, EntryRepository entryRepository, CommentRepository commentRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.entryRepository = entryRepository;
        this.commentRepository = commentRepository;
        this.roleRepository = roleRepository;
    }

    @Bean
    public CommandLineRunner initDatabase() {
        //prepare entities and save them into database
        prepareUtils();
        Iterable<User> users = initUsers();
        Iterable<Topic> topics = initTopics();
        Iterable<Entry> entries = initEntries();
        Iterable<Comment> comments = initComments();

        //display log info about saved entities
        users.forEach(user -> {
            log.info("Preloaded user: " + user.getUserName());
        });

        topics.forEach(topic -> {
            log.info("Preloaded topic: " + topic.getTitle());
        });

        entries.forEach(entry -> {
            log.info("Preloaded entry: " + entry.getTitle());
        });

        comments.forEach(comment -> {
            log.info("Preloaded comment: " + comment.getContent());
        });
        return args -> {};
    }

    private void prepareUtils() {
        // initialize data for role of user
        roleRepository.save(Role.builder().roleName("ADMIN").build());
        roleRepository.save(Role.builder().roleName("MODERATOR").build());
        roleRepository.save(Role.builder().roleName("REGULAR").build());

    }
    private Iterable<User> initUsers() {
        User adminUser = userRepository.save(
                User.builder()
                .userName("Romanello").firstName("Roman").lastName("Bułka").gender(Gender.MALE).email("roman@info.com").role(roleRepository.findByRoleName("ADMIN").get()).password("password").authorized(true)
                .build());
        User moderatorUser = userRepository.save(
                User.builder()
                .userName("Mała").firstName("Irenka").lastName("Buc-Pinda").gender(Gender.FEMALE).email("irenka@info.com").role(roleRepository.findByRoleName("MODERATOR").get()).password("password2").authorized(false)
                .build());
        User regularUser = userRepository.save(
                User.builder()
                .userName("Viola").firstName("Wioletta").lastName("Szczepanik").gender(Gender.FEMALE).email("viola@info.com").role(roleRepository.findByRoleName("REGULAR").get()).password("password3").authorized(true)
                .build());

        return Arrays.asList(adminUser, moderatorUser, regularUser);
    }

    private Iterable<Topic> initTopics() {
        User romanUser = userRepository.findByUserName("Romanello").get();
        User violaUser = userRepository.findByUserName("Viola").get();

        Topic firstTopic = topicRepository.save(
                Topic.builder()
                        .title("My pet is ill").category(Category.ANIMALS).date(LocalDateTime.now()).author(romanUser)
                        .build());

        Topic secondTopic = topicRepository.save(
                Topic.builder()
                        .title("I love my pet even it's ill").category(Category.ANIMALS).date(LocalDateTime.now()).author(violaUser)
                        .build());
        return Arrays.asList(firstTopic, secondTopic);
    }

    private Iterable<Entry> initEntries() {
        User romanUser = userRepository.findByUserName("Romanello").get();
        User malaUser = userRepository.findByUserName("Mała").get();
        User violaUser = userRepository.findByUserName("Viola").get();

        Topic firstTopic = topicRepository.findById(1L).get();
        Topic secondTopic = topicRepository.findById(2L).get();

        Entry firstEntry = entryRepository.save(
                Entry.builder().title("Help me cure my pet").author(romanUser).date(LocalDateTime.now()).topic(firstTopic)
                        .content("Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
                                "sed do eiusmod tempor incididunt ut labore et dolore magna " +
                                "aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                                "ullamco laboris nisi ut aliquip ex ea commodo consequat. Du" +
                                "is aute irure dolor in reprehenderit in voluptate velit ess" +
                                "e cillum dolore eu fugiat nulla pariatur. Excepteur sint oc" +
                                "caecat cupidatat non proident, sunt in culpa qui officia de" +
                                "serunt mollit anim id est laborum.")
                        .build());
        Entry secondEntry = entryRepository.save(
                Entry.builder().title("Do You recognize race of my dog").author(violaUser).date(LocalDateTime.now()).topic(secondTopic)
                        .content("Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
                                "sed do eiusmod tempor incididunt ut labore et dolore magna " +
                                "aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                                "ullamco laboris nisi ut aliquip ex ea commodo consequat. Du" +
                                "is aute irure dolor in reprehenderit in voluptate velit ess" +
                                "e cillum dolore eu fugiat nulla pariatur. Excepteur sint oc" +
                                "caecat cupidatat non proident, sunt in culpa qui officia de" +
                                "serunt mollit anim id est laborum.")
                        .build());
        Entry thirdEntry = entryRepository.save(
                Entry.builder().title("Nobody helps your pet").author(malaUser).date(LocalDateTime.now()).topic(firstTopic)
                        .content("Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
                                "sed do eiusmod tempor incididunt ut labore et dolore magna " +
                                "aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                                "ullamco laboris nisi ut aliquip ex ea commodo consequat. Du" +
                                "is aute irure dolor in reprehenderit in voluptate velit ess" +
                                "e cillum dolore eu fugiat nulla pariatur. Excepteur sint oc" +
                                "caecat cupidatat non proident, sunt in culpa qui officia de" +
                                "serunt mollit anim id est laborum.")
                        .build());
        return Arrays.asList(firstEntry, secondEntry, thirdEntry);
    }

    private Iterable<Comment> initComments() {
        User romanUser = userRepository.findByUserName("Romanello").get();
        User violaUser = userRepository.findByUserName("Viola").get();

        Entry firstEntry = entryRepository.findById(1L).get();
        Entry secondEntry = entryRepository.findById(2L).get();

        Comment firstComment = commentRepository.save(Comment.builder().author(romanUser).entry(secondEntry).date(LocalDateTime.now())
                .content("Bad comment")
                .build());
        Comment secondComment = commentRepository.save(Comment.builder().author(violaUser).entry(firstEntry).date(LocalDateTime.now())
                .content("Good comment")
                .build());
        return Arrays.asList(firstComment, secondComment);
    }
}
