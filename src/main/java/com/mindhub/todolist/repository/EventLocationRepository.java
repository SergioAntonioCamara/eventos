package com.mindhub.todolist.repository;

import com.mindhub.todolist.model.EventLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventLocationRepository extends JpaRepository<EventLocation, UUID> {
    //Optional<EventLocation>findById(UUID eventLocation_id);
}
