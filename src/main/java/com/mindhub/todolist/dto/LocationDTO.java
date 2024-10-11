package com.mindhub.todolist.dto;

import java.util.UUID;
//para usar en caso de mostrar datos de la ubicación sin detalles de un evento.
public class LocationDTO {
    private UUID location_id;
    private String name;
    private String location;
    private int capacity;
    private String img;


    public LocationDTO(UUID location_id, String name, String location, int capacity, String img) {
        this.location_id = location_id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.img = img;
    }


    public UUID getLocation_id() {
        return location_id;
    }

    public void setLocation_id(UUID location_id) {
        this.location_id = location_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}