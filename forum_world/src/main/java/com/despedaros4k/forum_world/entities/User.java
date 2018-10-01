package com.despedaros4k.forum_world.entities;

import com.despedaros4k.forum_world.entities.enums.Gender;
import com.despedaros4k.forum_world.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
}
