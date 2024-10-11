package com.mindhub.todolist.dto;

public class UserRegistrationDTO {
    private String name;
    private String email;
    private String password;
    private String rol;  // 'USER' para usuario normal o 'ORGANIZER' para organizador

    public UserRegistrationDTO() {
    }

    public UserRegistrationDTO(String name, String email, String password, String rol) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRole(String rol) {
        this.rol = rol;
    }
}
