package com.despedaros4k.forum_world.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name", unique = true)
    private String roleName;

    @OneToOne(mappedBy = "role", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnore
    private User user;
}
