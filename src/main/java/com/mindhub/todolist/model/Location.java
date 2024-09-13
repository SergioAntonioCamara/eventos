package com.mindhub.todolist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Location {
    @Id
    @GeneratedValue
    private UUID location_id;

    private String name;
    private String location;
    private int capacity;
    private String img;

    public Location() {
    }

    public Location(UUID location_id, String name, String location, int capacity, String img) {
        this.location_id = location_id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.img = img;
    }

    // Getters
    public UUID getLocation_id() {
        return location_id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getImg() {
        return img;
    }

    // Setters
    public void setLocation_id(UUID location_id) {
        this.location_id = location_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
