package com.mindhub.todolist.model;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.UUID;
import java.time.LocalDate;
@Entity
public class EventLocation {
    @Id
    @GeneratedValue
    private UUID eventLocation_id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    private int assistance;

    public EventLocation() {
    }

    public EventLocation(Event event_id, Location location_id, LocalDate date, int assistance) {
        this.eventLocation_id = UUID.randomUUID(); // Generar un UUID aleatorio para eventLocation_id
        this.event = event_id;
        this.location = location_id;
        this.date = date;
        this.assistance = assistance;
    }

    public UUID getEventLocation_id() {
        return eventLocation_id;
    }

    public void setEventLocation_id(UUID eventLocation_id) {
        this.eventLocation_id = eventLocation_id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAssistance() {
        return assistance;
    }

    public void setAssistance(int assistance) {
        this.assistance = assistance;
    }

}
