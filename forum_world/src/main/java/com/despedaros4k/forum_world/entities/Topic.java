package com.despedaros4k.forum_world.entities;

import com.despedaros4k.forum_world.entities.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    private LocalDateTime date;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "topic",
            cascade = CascadeType.ALL
    )
    private Collection<Entry> entries;
    @ManyToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            }

    )
    @JoinColumn(name = "user_id")
    private User author;

    public Topic(String title, Category category, LocalDateTime date, User author) {
        this.title = title;
        this.category = category;
        this.date = date;
        this.author = author;
    }
}
