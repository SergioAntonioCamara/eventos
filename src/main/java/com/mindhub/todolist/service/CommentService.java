package com.mindhub.todolist.service;

import com.mindhub.todolist.dto.CommentDTO;
import com.mindhub.todolist.model.Comment;
import com.mindhub.todolist.model.Customer;
import com.mindhub.todolist.model.Event;
import com.mindhub.todolist.repository.CommentRepository;
import com.mindhub.todolist.repository.CustomerRepository;
import com.mindhub.todolist.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EventRepository eventRepository;

    //método para guardar comentarios
    public Comment saveComment(Comment comment){
        return commentRepository.save(comment);
    }

    //listado de comentarios por evento
    public List<CommentDTO> getCommentsByEvent(UUID eventID){
        Event event = eventRepository.findById(eventID)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        List<Comment> comments = commentRepository.findByEvent(event);
        return comments.stream().map(comment -> {
            Customer customer = customerRepository.findById(
                    comment.getCustomer().getCustomer_id())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            return new CommentDTO(
                            comment.getComment_id(),
                            comment.getComment(),
                            customer.getName(),
                            comment.getEvent().getName()
                    );
                }).collect(Collectors.toList());

    }

    // listado de comentarios por cliente
    public List<CommentDTO> getCommentsByCustomer(UUID customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        List<Comment> comments = commentRepository.findByCustomer(customer);
        return comments.stream().map(comment -> new CommentDTO(
                comment.getComment_id(),
                comment.getComment(),
                customer.getName(),
                comment.getEvent().getName()
        )).collect(Collectors.toList());
    }
}
