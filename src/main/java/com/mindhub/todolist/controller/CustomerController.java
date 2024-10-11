package com.mindhub.todolist.controller;
import com.mindhub.todolist.dto.UserRegistrationDTO;
import com.mindhub.todolist.model.Customer;
import com.mindhub.todolist.repository.CustomerRepository;
import com.mindhub.todolist.service.CustomerService;
import com.mindhub.todolist.dto.CustomerBasicDTO;
import com.mindhub.todolist.dto.CustomerDetailsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/basic")
    public List<CustomerBasicDTO> getAllCustomersBasic() {
        return customerService.getAllCustomersBasic();
    }

    @GetMapping("/{customerId}")
    public CustomerDetailsDTO getCustomerDetails(@PathVariable UUID customerId) {
        return customerService.getCustomerDetailsById(customerId);
    }

    /*@PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        try {
            Customer customer = customerService.registerUser(userRegistrationDTO);
            return ResponseEntity.ok("Usuario registrado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/

    @PostMapping("/register")
    public ResponseEntity<Customer> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        try {
            Customer customer = customerService.registerUser(userRegistrationDTO);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{customerId}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable UUID customerId, @RequestBody String newPassword) {
        customerService.updatePassword(customerId, newPassword);
        return ResponseEntity.ok().build(); // Tendría que devolverme un estado 200 OK, contraseña cambiada.
    }
}
