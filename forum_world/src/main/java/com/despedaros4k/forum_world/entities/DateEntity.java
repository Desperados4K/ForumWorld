package com.despedaros4k.forum_world.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class DateEntity extends BaseEntity {

    private LocalDateTime date;
}
