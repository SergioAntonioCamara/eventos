package com.mindhub.todolist.repository;

import com.mindhub.todolist.model.CustomerEvent;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface CustomerEventRepository extends JpaRepository<CustomerEvent, UUID> {
}
