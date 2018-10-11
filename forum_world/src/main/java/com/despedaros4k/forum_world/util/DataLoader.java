package com.despedaros4k.forum_world.util;

import com.despedaros4k.forum_world.entities.*;
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
    private GenderRepository genderRepository;
    private CategoryRepository categoryRepository;

    public DataLoader(UserRepository userRepository, TopicRepository topicRepository, EntryRepository entryRepository, CommentRepository commentRepository, RoleRepository roleRepository, GenderRepository genderRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.entryRepository = entryRepository;
        this.commentRepository = commentRepository;
        this.roleRepository = roleRepository;
        this.genderRepository = genderRepository;
        this.categoryRepository = categoryRepository;
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

        //initialize data for gender of user
        genderRepository.save(Gender.builder().genderName("FEMALE").build());
        genderRepository.save(Gender.builder().genderName("MALE").build());

        //initialize data for topic categories
        categoryRepository.save(Category.builder().categoryName("Java").build());
        categoryRepository.save(Category.builder().categoryName("Python").build());
        categoryRepository.save(Category.builder().categoryName("C++").build());

    }
    private Iterable<User> initUsers() {
        User adminUser = userRepository.save(
                // creating new user by user builder
                User.builder()
                .userName("Romanello").firstName("Roman").lastName("Bułka").gender(genderRepository.findByGenderName("MALE").get()).email("roman@info.com").role(roleRepository.findByRoleName("ADMIN").get()).password("password").authorized(true)
                .build());
        User moderatorUser = userRepository.save(
                User.builder()
                .userName("Mała").firstName("Irenka").lastName("Buc-Pinda").gender(genderRepository.findByGenderName("FEMALE").get()).email("irenka@info.com").role(roleRepository.findByRoleName("MODERATOR").get()).password("password2").authorized(false)
                .build());
        User regularUser = userRepository.save(
                User.builder()
                .userName("Viola").firstName("Wioletta").lastName("Szczepanik").gender(genderRepository.findByGenderName("FEMALE").get()).email("viola@info.com").role(roleRepository.findByRoleName("REGULAR").get()).password("password3").authorized(true)
                .build());

        return Arrays.asList(adminUser, moderatorUser, regularUser);
    }
    //todo dokonczyc zamiane enumow i rozszerzanie baseentity
    private Iterable<Topic> initTopics() {
        User romanUser = userRepository.findByUserName("Romanello").get();
        User violaUser = userRepository.findByUserName("Viola").get();

        Topic firstTopic = topicRepository.save(
                Topic.builder()
                        .title("My code not works").category(categoryRepository.findByCategoryName("Java").get()).date(LocalDateTime.now()).author(romanUser)
                        .build());

        Topic secondTopic = topicRepository.save(
                Topic.builder()
                        .title("I can't split the string").category(categoryRepository.findByCategoryName("Python").get()).date(LocalDateTime.now()).author(violaUser)
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
                Entry.builder().title("Help in code").author(romanUser).date(LocalDateTime.now()).topic(firstTopic)
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
                Entry.builder().title("Did you try slice?").author(violaUser).date(LocalDateTime.now()).topic(secondTopic)
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
                Entry.builder().title("Java dies").author(malaUser).date(LocalDateTime.now()).topic(firstTopic)
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
