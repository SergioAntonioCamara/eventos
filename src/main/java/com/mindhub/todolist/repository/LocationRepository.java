package com.mindhub.todolist.repository;

import com.mindhub.todolist.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {
    List<Location> findByName(String name);
   Optional<Location> findById(UUID location_id);
}
