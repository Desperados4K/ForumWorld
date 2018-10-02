package com.despedaros4k.forum_world.util;

import com.despedaros4k.forum_world.entities.Comment;
import com.despedaros4k.forum_world.entities.Entry;
import com.despedaros4k.forum_world.entities.Topic;
import com.despedaros4k.forum_world.entities.User;
import com.despedaros4k.forum_world.entities.enums.Category;
import com.despedaros4k.forum_world.entities.enums.Gender;
import com.despedaros4k.forum_world.entities.enums.Role;
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
    private RatingRepository ratingRepository;

    public DataLoader(UserRepository userRepository, TopicRepository topicRepository, EntryRepository entryRepository, CommentRepository commentRepository, RatingRepository ratingRepository) {
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.entryRepository = entryRepository;
        this.commentRepository = commentRepository;
        this.ratingRepository = ratingRepository;
    }

    @Bean
    public CommandLineRunner initDatabase() {
        Iterable<User> users = initUsers();
        Iterable<Topic> topics = initTopics();
        Iterable<Entry> entries = initEntries();
        Iterable<Comment> comments = initComments();
        users.forEach(user -> {
            log.info("Preloaded user: " + user.getUserName());
        });
        topics.forEach(topic -> {
            log.info("Preloaded topic: " + topic.getTitle());
        });
        return args -> {
        };
    }

    private Iterable<User> initUsers() {
        User adminUser = userRepository.save(
                User.builder()
                .userName("Romanello").firstName("Roman").lastName("Bułka").gender(Gender.MALE).email("roman@info.com").role(Role.ADMIN).password("password").authorized(true)
                .build());
        User moderatorUser = userRepository.save(
                User.builder()
                .userName("Mała").firstName("Irenka").lastName("Buc-Pinda").gender(Gender.FEMALE).email("irenka@info.com").role(Role.MODERATOR).password("password2").authorized(false)
                .build());
        User regularUser = userRepository.save(
                User.builder()
                .userName("Viola").firstName("Wioletta").lastName("Szczepanik").gender(Gender.FEMALE).email("viola@info.com").role(Role.REGULAR).password("password3").authorized(true)
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
        return null;
    }

    private Iterable<Comment> initComments() {
        return null;
    }
}
