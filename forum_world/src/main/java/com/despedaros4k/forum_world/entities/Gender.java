package com.despedaros4k.forum_world.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Gender extends BaseEntity {

    @Column(name = "gender_name", unique = true)
    private String genderName;

    @OneToMany(mappedBy = "gender", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnore
    private List<User> user;
}
