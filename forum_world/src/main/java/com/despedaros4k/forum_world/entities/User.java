package com.despedaros4k.forum_world.entities;

import com.despedaros4k.forum_world.entities.enums.Gender;
import com.despedaros4k.forum_world.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{

    private String userName;
    private String firstName;
    private String lastName;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private String email;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private String password;
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
