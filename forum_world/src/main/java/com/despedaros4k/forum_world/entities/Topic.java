package com.despedaros4k.forum_world.entities;

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
public class Topic extends BaseEntity{

    @Column(name = "title")
    private String title;

    @JoinColumn(name = "category")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private Category category;

    @Column(name = "date")
    private LocalDateTime date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topic", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<Entry> entries;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User author;

    public Topic(String title, Category category, LocalDateTime date, User author) {
        this.title = title;
        this.category = category;
        this.date = date;
        this.author = author;
    }
}
