package com.despedaros4k.forum_world.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
            fetch = FetchType.LAZY,
            mappedBy = "entry",
            cascade = CascadeType.ALL
    )
    @JsonIgnore
    private Collection<Comment> comments;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            }

    )
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User author;
    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            }

    )
    @JoinColumn(name = "topic_id")
    @JsonIgnore
    private Topic topic;
}
