package com.despedaros4k.forum_world.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entry  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Lob
    private String content;

    private LocalDateTime date;

    @OneToMany(
            mappedBy = "entry",
            cascade = CascadeType.ALL
    )
    private Collection<Comment> comments;

    @ManyToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            }

    )
    @JoinColumn(name = "user_id")
    private User author;
    @ManyToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            }

    )
    @JoinColumn(name = "topic_id")
    private Topic topic;

    public Entry(String title, String content, LocalDateTime date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }
}
