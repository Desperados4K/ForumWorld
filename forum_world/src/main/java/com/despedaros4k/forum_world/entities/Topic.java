package com.despedaros4k.forum_world.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topic extends InscriptionEntity {

    private String title;
    //todo add Category class
    //    private Category category;
    private Collection<Entry> entries;
}
