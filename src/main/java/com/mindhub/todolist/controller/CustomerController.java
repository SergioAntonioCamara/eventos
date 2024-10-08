package com.mindhub.todolist.controller;

import com.mindhub.todolist.dto.UserRegistrationDTO;
import com.mindhub.todolist.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        try {
            customerService.registerUser(userRegistrationDTO);
            return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping ("/login")
    public String showLoginPage() {
        return "login"; // hacer para el front vista `login.html`
    }
}


