package com.mindhub.todolist.repository;

import com.mindhub.todolist.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID>{
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findById(UUID id);
}
