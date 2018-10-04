package com.despedaros4k.forum_world.resourceAssemblers;

import com.despedaros4k.forum_world.controllers.CommentController;
import com.despedaros4k.forum_world.entities.Comment;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CommentResourceAssembler implements ResourceAssembler<Comment, Resource<Comment>> {
    @Override
    public Resource<Comment> toResource(Comment comment) {
        return new Resource<>(comment,
                linkTo(methodOn(CommentController.class).oneComment(comment.getId())).withSelfRel(),
                linkTo(methodOn(CommentController.class).allComments()).withRel("comments"));
    }
}
