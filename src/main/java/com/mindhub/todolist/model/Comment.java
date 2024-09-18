package com.mindhub.todolist.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private UUID comment_id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    private String comment;

    public Comment() {
    }

    public Comment(UUID comment_id, Customer customer, Event event, String comment) {
        this.comment_id = comment_id;
        this.customer = customer;
        this.event = event;
        this.comment = comment;
    }

    public UUID getComment_id() {
        return comment_id;
    }

    public void setComment_id(UUID comment_id) {
        this.comment_id = comment_id;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
