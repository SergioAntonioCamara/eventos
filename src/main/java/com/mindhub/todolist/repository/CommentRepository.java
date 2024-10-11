package com.mindhub.todolist.repository;

import com.mindhub.todolist.model.Comment;
import com.mindhub.todolist.model.Customer;
import com.mindhub.todolist.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    Optional<Comment> findById(UUID comment_id);
    // Encontrar comentarios por evento
    List<Comment> findByEvent(Event event);

    // Encontrar comentarios por cliente
    List<Comment> findByCustomer(Customer customer);

    // Encontrar comentarios por cliente y evento
    List<Comment> findByCustomerAndEvent(Customer customer, Event event);
}
