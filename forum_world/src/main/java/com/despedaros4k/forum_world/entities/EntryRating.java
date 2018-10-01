package com.despedaros4k.forum_world.entities;

import javax.persistence.*;

@Entity
@Table(name = "entry_ratings")
public class EntryRating extends Rating{
    public EntryRating(Long id, Integer thumbUp, Integer thumbDown) {
        super(id, thumbUp, thumbDown);
    }
    @JoinColumn(name = "entry_id",
            nullable = false
    )
    @OneToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            })
    private Entry entry;
}
