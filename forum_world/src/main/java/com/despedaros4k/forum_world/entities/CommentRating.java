package com.despedaros4k.forum_world.entities;

import javax.persistence.*;

@Entity
@Table(name = "comment_ratings")
public class CommentRating extends Rating {
    public CommentRating(Long id, Integer thumbUp, Integer thumbDown) {
        super(id, thumbUp, thumbDown);
    }
    @JoinColumn(name = "comment_id",
            nullable = false
    )
    @OneToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            })
    private Comment comment;
}
