package com.mindhub.todolist.service;

import com.mindhub.todolist.dto.UserRegistrationDTO;
import com.mindhub.todolist.model.Customer;
import com.mindhub.todolist.model.Rol;
import com.mindhub.todolist.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService{


    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void registerUser(UserRegistrationDTO userRegistrationDTO){

        //verificar que el correo sea nuevo
        Optional<Customer> existingCustomer = customerRepository.findByEmail(userRegistrationDTO.getEmail());
        if (existingCustomer.isPresent()){
            throw new IllegalStateException("El correo ya está en uso.");
        }

        if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())) {
            throw new IllegalStateException("Las contraseñas no coinciden.");
        }
        // Codificar la contraseña antes de guardar el usuario
        String encodedPassword = passwordEncoder.encode(userRegistrationDTO.getPassword());
        //Para crear un nuevo usuario a partir del DTO:
        Customer newCustomer = new Customer();
        newCustomer.setName(userRegistrationDTO.getName());
        newCustomer.setLastName(userRegistrationDTO.getLastName());
        newCustomer.setEmail(userRegistrationDTO.getEmail());
        newCustomer.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        newCustomer.setRol(userRegistrationDTO.getRol());

        /* Definir el rol
        if (userRegistrationDTO.getRol().equals("ORGANIZER")) {
            newCustomer.setRol(Rol.ORGANIZER);
        } else {
            newCustomer.setRol(Rol.USER);
        }*/

        //Guardar:
        customerRepository.save(newCustomer);
    }
}