package com.despedaros4k.forum_world.entities;

import com.despedaros4k.forum_world.entities.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topic extends DateEntity {

    private String title;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    private Collection<Entry> entries;
    private User author;
}
