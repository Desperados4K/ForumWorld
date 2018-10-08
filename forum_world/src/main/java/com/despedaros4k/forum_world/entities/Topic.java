package com.despedaros4k.forum_world.entities;

import com.despedaros4k.forum_world.entities.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    private LocalDateTime date;

    public Topic(String title, Category category, LocalDateTime date, User author) {
        this.title = title;
        this.category = category;
        this.date = date;
        this.author = author;
    }
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "topic",
            cascade = CascadeType.ALL
    )
    @JsonIgnore
    private Collection<Entry> entries;

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
}
