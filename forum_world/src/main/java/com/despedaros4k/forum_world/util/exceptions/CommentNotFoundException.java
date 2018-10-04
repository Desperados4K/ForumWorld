package com.despedaros4k.forum_world.util.exceptions;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(Long id) {
        super("Could not find comment: " + id);
    }
}
