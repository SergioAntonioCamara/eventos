package com.mindhub.todolist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private UUID customer_id;
    private String name;
    private String lastName;
    private String email;
    private boolean activated;
    private String password;
    private short edad;
    private Genero genero;
    private Rol rol;

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
}
