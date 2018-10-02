package com.despedaros4k.forum_world.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
@Inheritance (
        strategy = InheritanceType.TABLE_PER_CLASS
)
@Getter
@Setter
@NoArgsConstructor
public class Rating {
    public Rating(Integer thumbUp, Integer thumbDown) {
        this.thumbUp = thumbUp;
        this.thumbDown = thumbDown;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Integer thumbUp;
    private Integer thumbDown;
}
