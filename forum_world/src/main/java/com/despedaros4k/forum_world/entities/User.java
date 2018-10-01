package com.despedaros4k.forum_world.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    //todo add Gender enum
    //private Gender gender;
    private String email;
    //todo add Role enum
    // private Role role;
    private String password;
    private boolean authorized;
}
