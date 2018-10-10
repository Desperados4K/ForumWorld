package com.despedaros4k.forum_world.entities;

import com.despedaros4k.forum_world.entities.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @JoinColumn(name = "role")
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private Role role;

    @Column(name = "password")
    private String password;

    @Column(name = "authorized")
    private boolean authorized;


    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "author",
            cascade = CascadeType.ALL
    )
    @JsonIgnore
    private Collection<Topic> topics;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "author",
            cascade = CascadeType.ALL
    )
    @JsonIgnore
    private Collection<Entry> entries;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "author",
            cascade = CascadeType.ALL
    )
    @JsonIgnore
    private Collection<Comment> comments;

    public User(String userName, String firstName, String lastName, Gender gender, String email, Role role, String password, boolean authorized) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.role = role;
        this.password = password;
        this.authorized = authorized;
    }
}
