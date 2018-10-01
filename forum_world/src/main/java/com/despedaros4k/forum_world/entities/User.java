package com.despedaros4k.forum_world.entities;

import com.despedaros4k.forum_world.entities.enums.Gender;
import com.despedaros4k.forum_world.entities.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    public User(String userName, String firstName, String lastName, Gender gender, @NotEmpty(message = "Please provide an e-mail") @Email(message = "Please provide a valid e-mail") String email, Role role, String password, boolean authorized) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.role = role;
        this.password = password;
        this.authorized = authorized;
    }

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
    @NotEmpty(message = "Please provide an e-mail")
    @Email(message = "Please provide a valid e-mail")
    private String email;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "password")
    private String password;

    @Column(name = "authorized")
    private boolean authorized;


    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "author",
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    private Collection<Topic> topics;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "author",
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    private Collection<Entry> entries;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "author",
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    private Collection<Comment> comments;
}
