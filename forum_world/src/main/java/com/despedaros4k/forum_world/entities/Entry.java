package com.despedaros4k.forum_world.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Entry extends InscriptionEntity {

    private String title;
    @Lob
    private String content;
    //todo Rating class
    //private Rating rating;
    private Collection<Comment> comments;
}
