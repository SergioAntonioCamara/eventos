package com.mindhub.todolist.service;

import com.mindhub.todolist.dto.CustomerBasicDTO;
import com.mindhub.todolist.dto.CustomerDetailsDTO;
import com.mindhub.todolist.dto.UserRegistrationDTO;
import com.mindhub.todolist.model.Customer;
import com.mindhub.todolist.model.Rol;
import com.mindhub.todolist.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CustomerService (CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new org.springframework.security.core.userdetails.User(
                customer.getEmail(),
                customer.getPassword(),
                getAuthorities(customer.getRol())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Rol rol) {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }

    public List<CustomerBasicDTO> getAllCustomersBasic() {
        return customerRepository.findAll().stream()
                .map(customer -> new CustomerBasicDTO(
                        customer.getCustomer_id(),
                        customer.getName(),
                        customer.getLastName(),
                        customer.getEmail()
                )).collect(Collectors.toList());
    }

    public CustomerDetailsDTO getCustomerDetailsById(UUID customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        return new CustomerDetailsDTO(
                customer.getCustomer_id(),
                customer.getName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getEdad()
                //customer.getEvents().stream().map(event -> new EventDTO(event.getEventId(), event.getTitle())).collect(Collectors.toList())
        );
    }

    public Customer registerUser(UserRegistrationDTO userRegistrationDTO) {
        if (customerRepository.findByEmail(userRegistrationDTO.getEmail()).isPresent()) {
            throw new RuntimeException("El correo ya está en uso");
        }

        // Codificar la contraseña
        String encodedPassword = passwordEncoder.encode(userRegistrationDTO.getPassword());

        // Crear el nuevo usuario
        Customer customer = new Customer(
                userRegistrationDTO.getEmail(),
                userRegistrationDTO.getEmail(),
                encodedPassword,
                Rol.valueOf(userRegistrationDTO.getRol())
        );

        return customerRepository.save(customer);

    }

    //Actualizar contraseña
    public void updatePassword(UUID customerId, String newPassword) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Codificar la nueva contraseña
        String encodedPassword = passwordEncoder.encode(newPassword);
        customer.setPassword(encodedPassword);

        customerRepository.save(customer);
    }
}
