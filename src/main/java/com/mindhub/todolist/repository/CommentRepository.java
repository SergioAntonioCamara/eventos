package com.mindhub.todolist.repository;

import com.mindhub.todolist.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

    Optional<Comment> findById(UUID comment_id);
}
