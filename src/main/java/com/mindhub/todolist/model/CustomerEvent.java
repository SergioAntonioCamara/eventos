package com.mindhub.todolist.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class CustomerEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID user_id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public CustomerEvent() {
    }

    public CustomerEvent(Customer customer, Event event) { //no coloco user_id porque se genera automáticamente
        this.customer = customer;
        this.event = event;
    }

    // Getters y Setters
    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public Customer getCustomer() {

        return customer;
    }

    public void setCustomer(Customer customer) {

        this.customer = customer;
    }

    public Event getEvent() {

        return event;
    }

    public void setEvent(Event event) {

        this.event = event;
    }

}
