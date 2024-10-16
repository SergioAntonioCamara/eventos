package com.mindhub.todolist.repository;

import com.mindhub.todolist.model.Customer;
import com.mindhub.todolist.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    Optional<Event> findById(UUID event_id);
    List<Event> findByNameContainingIgnoreCase(String name);
}
