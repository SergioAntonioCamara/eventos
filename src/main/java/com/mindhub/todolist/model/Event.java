package com.mindhub.todolist.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Event {
    @Id
    @GeneratedValue
    private UUID event_id;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private Customer organizer;

    private String desc;
    private String img;
    private short age_req;
    private String name;

    public Event() {
    }

    public Event(UUID event_id, Customer organizer, String desc, String img, short age_req, String name) {
        this.event_id = event_id;
        this.organizer = organizer;
        this.desc = desc;
        this.img = img;
        this.age_req = age_req;
        this.name = name;
    }

    public UUID getEvent_id() {
        return event_id;
    }

    public void setEvent_id(UUID event_id) {
        this.event_id = event_id;
    }

    public Customer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Customer organizer) {
        this.organizer = organizer;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public short getAge_req() {
        return age_req;
    }

    public void setAge_req(short age_req) {
        this.age_req = age_req;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
