package com.mindhub.todolist.dto;

import com.mindhub.todolist.model.Genero;
import com.mindhub.todolist.model.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegistrationDTO {
    @NotNull(message = "El nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String name;
    @NotNull(message = "El apellido es obligatorio")
    @Size(min = 3, max = 50, message = "El apellido debe tener entre 3 y 50 caracteres")
    private String lastName;
    @NotNull(message = "El correo es obligatorio")
    @Email(message = "El correo debe tener un formato válido")
    private String email;
    @NotNull(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;
    private short edad;
    private Genero genero;
    @NotNull(message = "El rol es obligatorio")
    private Rol rol;
    private String confirmPassword;

    // Getters y setters

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

    //para asegurar que el usuario ingrese la contraseña correctamente:
    public String getConfirmPassword(){
        return confirmPassword;
    }

    public void setConfirmPassword (String confirmPassword){
        this.confirmPassword = confirmPassword;
    }
}
