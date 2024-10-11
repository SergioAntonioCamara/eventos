package com.mindhub.todolist.model;

import aj.org.objectweb.asm.commons.GeneratorAdapter;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mindhub.todolist.model.Rol.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID customer_id;
    private String name;
    private String lastName;
    private String email;
    private boolean activated;
    private String password;
    private short edad;

    private Genero genero;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    public static Rol getRolFromString(String rol) {
        return Rol.valueOf(rol.toUpperCase());
    }

    @OneToMany(mappedBy = "customer")
    private List<CustomerEvent> customerEvents = new ArrayList<>();

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Customer() {
    }

    public Customer(UUID customer_id, String name, String lastName, String email, boolean activated, String password, short edad, Genero genero, Rol rol) {
        this.customer_id = customer_id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.activated = activated;
        this.password = password;
        this.edad = edad;
        this.genero = genero;
        this.rol = rol;
    }

    public Customer(String email, String name, String password, Rol rol) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.rol = rol;
        this.activated = false;
    }

    public UUID getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(UUID customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getEdad() {
        return edad;
    }

    public void setEdad(short edad) {
        this.edad = edad;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<CustomerEvent> getCustomerEvents() {
        return customerEvents;
    }

    public void setCustomerEvents(List<CustomerEvent> customerEvents) {
        this.customerEvents = customerEvents;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event) {
        events.add(event);
        event.setOrganizer(this);
    }

    public void removeEvent(Event event) {
        events.remove(event);
        event.setOrganizer(null);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
