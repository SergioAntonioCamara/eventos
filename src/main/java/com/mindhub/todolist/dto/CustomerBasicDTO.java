package com.mindhub.todolist.dto;

import java.util.UUID;

public class CustomerBasicDTO {
    private UUID customer_id;
    private String name;
    private String lastName;
    private String email;

    public CustomerBasicDTO(UUID customer_id, String name, String lastName, String email) {
        this.customer_id = customer_id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    // Getters and Setters
    public UUID getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(UUID customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
