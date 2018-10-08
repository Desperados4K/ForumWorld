package com.despedaros4k.forum_world.services;

import com.despedaros4k.forum_world.controllers.CommentController;
import com.despedaros4k.forum_world.entities.Comment;
import com.despedaros4k.forum_world.repositories.CommentRepository;
import com.despedaros4k.forum_world.resourceAssemblers.CommentResourceAssembler;
import com.despedaros4k.forum_world.util.exceptions.CommentNotFoundException;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public class CommentRestService implements RestService<Comment> {

    private final CommentRepository commentRepository;
    private final CommentResourceAssembler commentResourceAssembler;

    public CommentRestService(CommentRepository commentRepository, CommentResourceAssembler commentResourceAssembler) {
        this.commentRepository = commentRepository;
        this.commentResourceAssembler = commentResourceAssembler;
    }

    @Override
    public Resources<Resource<Comment>> findAll() {
        List<Resource<Comment>> comments = commentRepository
                .findAll()
                .stream()
                .map(commentResourceAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(comments,
                linkTo(methodOn(CommentController.class).allComments()).withRel("comments"));
    }

    @Override
    public Resource<Comment> findById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
        return commentResourceAssembler.toResource(comment);
    }

    @Override
    public Resource<Comment> save(Comment entity) {
        return commentResourceAssembler.toResource(commentRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {

    }
}
