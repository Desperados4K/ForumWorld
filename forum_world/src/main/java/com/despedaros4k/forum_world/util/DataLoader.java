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
        User adminUser = userRepository.save(new User("Romanello", "Roman", "Bułka", Gender.MALE, "roman@info.com", Role.ADMIN, "password", true));
        User moderatorUser  = userRepository.save(new User("Mała", "Irenka", "Buc-Pinda", Gender.FEMALE, "irenka@info.com", Role.MODERATOR, "password2", false));
        User regularUser = userRepository.save(new User("Viola", "Wioletta", "Szczepanik", Gender.FEMALE, "wiole@info.com", Role.REGULAR, "password3", true));
        return userRepository.findAll();
    }

    private Iterable<Topic> initTopics() {
        User romanUser = userRepository.findByUserName("Romanello").get();
        User violaUser = userRepository.findByUserName("Viola").get();
        Topic firstTopic = topicRepository.save(new Topic("My pet is ill", Category.ANIMALS, LocalDateTime.now(), romanUser));
        Topic secondTopic = topicRepository.save(new Topic("I love my pet even it's ill", Category.ANIMALS, LocalDateTime.now(), violaUser));


        return topicRepository.findAll();
    }

    private Iterable<Entry> initEntries() {
        return null;
    }

    private Iterable<Comment> initComments() {
        return null;
    }
}
