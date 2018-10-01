package com.despedaros4k.forum_world.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class InscriptionEntity extends BaseEntity {

    private LocalDateTime date;
    private User author;
}
