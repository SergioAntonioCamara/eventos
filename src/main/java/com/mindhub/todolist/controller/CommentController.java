package com.mindhub.todolist.controller;

import com.mindhub.todolist.dto.CommentDTO;
import com.mindhub.todolist.model.Comment;
import com.mindhub.todolist.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //endpoint para ver comentario por evento
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByEvent(@PathVariable UUID eventId){
        List<CommentDTO> comments = commentService.getCommentsByEvent(eventId);
        return ResponseEntity.ok(comments);
    }

    //endpoint para ver comentarios de un usuario
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByCustomer(@PathVariable UUID customerId){
        List<CommentDTO> comments = commentService.getCommentsByCustomer(customerId);
        return ResponseEntity.ok(comments);
    }

    //endpoint para guardar comentarios
     @PostMapping("/saveComment")
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment){
        Comment savedComment = commentService.saveComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
     }
}
