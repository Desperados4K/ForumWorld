package com.despedaros4k.forum_world.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.stream.events.Comment;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Lob
    private String content;
    //todo Rating class
    //private Rating rating;
    private LocalDateTime date;
    private Collection<Comment> comments;
    private User author;
}
