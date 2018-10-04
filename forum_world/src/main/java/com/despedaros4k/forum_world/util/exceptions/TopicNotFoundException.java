package com.despedaros4k.forum_world.util.exceptions;

public class TopicNotFoundException extends RuntimeException {
    public TopicNotFoundException(Long id) {
        super("Could not find topic: " + id);
    }
}
