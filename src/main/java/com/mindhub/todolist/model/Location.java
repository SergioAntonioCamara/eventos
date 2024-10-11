package com.mindhub.todolist.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID location_id;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true) //cascade = CascadeType.ALL, orphanRemoval = true: maneja automáticamente la persistencia de ubicaciones de eventos y la eliminación de ubicaciones huérfanas.
    private List<EventLocation> eventLocations = new ArrayList<>();

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

    public List<EventLocation> getEventLocations() {
        return eventLocations;
    }

    public void setEventLocations(List<EventLocation> eventLocations) {
        this.eventLocations = eventLocations;
    }


/* INICIA: Cuando se agrega o elimina una ubicación de evento, la ubicación se actualiza en el EventLocation Y Location */
    public void addEventLocation(EventLocation eventLocation) {
        eventLocations.add(eventLocation);
        eventLocation.setLocation(this);
    }

    public void removeEventLocation(EventLocation eventLocation) {
        eventLocations.remove(eventLocation);
        eventLocation.setLocation(null);
    }
    /* TERMINA: Cuando se agrega o elimina una ubicación de evento, la ubicación se actualiza en el EventLocation Y Location*/
}
