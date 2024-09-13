package com.mindhub.todolist.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class CustomerEvent {
    @Id
    @GeneratedValue
    private UUID userEvent_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public CustomerEvent() {
    }

    public CustomerEvent(UUID userEvent_id, Customer customer, Event event) {
        this.userEvent_id = userEvent_id;
        this.customer = customer;
        this.event = event;
    }

    public UUID getUserEvent_id() {
        return userEvent_id;
    }

    public void setUserEvent_id(UUID userEvent_id) {
        this.userEvent_id = userEvent_id;
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
