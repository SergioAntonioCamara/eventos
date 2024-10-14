package com.mindhub.todolist.dto;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public class CustomerDetailsDTO {
    private UUID customer_id;
    private String name;
    private String lastName;
    private String email;
    private short edad;
    //private List<EventDTO> events; // Lista de eventos que organiza

    public CustomerDetailsDTO(UUID customer_id, String name, String lastName, String email, short edad/*, List<EventDTO> events*/) {
        this.customer_id = customer_id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.edad = edad;
        //this.events = events;
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

    public short getEdad() {
        return edad;
    }

    public void setEdad(short edad) {
        this.edad = edad;
    }
/*
    public List<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(List<EventDTO> events) {
        this.events = events;
    }*/
}
