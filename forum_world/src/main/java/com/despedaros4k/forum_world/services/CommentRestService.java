package com.despedaros4k.forum_world.services;

import com.despedaros4k.forum_world.controllers.CommentController;
import com.despedaros4k.forum_world.entities.Comment;
import com.despedaros4k.forum_world.repositories.CommentRepository;
import com.despedaros4k.forum_world.resourceAssemblers.CommentResourceAssembler;
import com.despedaros4k.forum_world.util.exceptions.CommentNotFoundException;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public Resource<Comment> update(Comment entity, Long id) {
        Comment updatedComment = commentRepository.findById(id)
                .map(comment -> {
                    comment.setContent(entity.getContent());
                    comment.setDate(entity.getDate());
                    return commentRepository.save(comment);
                })
                .orElseGet(() -> {
                    //todo chyba to jest nie potrzebne bo z automatu to i tak nadaje inne id
                    entity.setId(id);
                    return commentRepository.save(entity);
                        }
                );
        return  commentResourceAssembler.toResource(updatedComment);
    }

    @Override
    public ResponseEntity deleteById(Long id) {
        Optional<Comment> commentToDelete = commentRepository.findById(id);
        if (commentToDelete.isPresent()) {
            Comment comment = commentToDelete.get();
            commentRepository.deleteById(comment.getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError(
                "Method not allowed",
                "There is no comment with the id: " + id));
    }
}
