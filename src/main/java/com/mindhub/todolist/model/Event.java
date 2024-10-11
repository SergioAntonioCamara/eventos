package com.mindhub.todolist.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID event_id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer organizer;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true) //cascade = CascadeType.ALL y orphanRemoval = true: maneja automáticamente la persistencia de comentarios y la eliminación de comentarios huérfanos.
    private List<EventLocation> eventLocations = new ArrayList<>();

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

    public Customer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Customer organizer) {
        this.organizer = organizer;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    /* Comentarios inicia: Event y Comment Cuando se agrega o elimina un comentario, el evento se actualiza en el comentario y viceversa. */
    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setEvent(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setEvent(null);
    }
    /* Comentarios termina: Event y Comment Cuando se agrega o elimina un comentario, el evento se actualiza en el comentario y viceversa. */

    public void setEventLocations(List<EventLocation> eventLocations) {
        this.eventLocations = eventLocations;
    }
    /*INICIA: Cuando se agrega o elimina una ubicación de evento, el evento se actualiza en la ubicación de evento*/
    public void addEventLocation(EventLocation eventLocation) {
        eventLocations.add(eventLocation);
        eventLocation.setEvent(this);
    }

    public void removeEventLocation(EventLocation eventLocation) {
        eventLocations.remove(eventLocation);
        eventLocation.setEvent(null);
    }
    /*FIN: Cuando se agrega o elimina una ubicación de evento, el evento se actualiza en la ubicación de evento*/
}
