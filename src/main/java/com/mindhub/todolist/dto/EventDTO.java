package com.mindhub.todolist.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class EventDTO {
    private UUID id;
    private String name;
    private String date;
    private String location;
    private int availableSlots;

    // Constructor
    public EventDTO(UUID id, String name, String date, String location, int availableSlots) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.availableSlots = availableSlots;
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }
}
