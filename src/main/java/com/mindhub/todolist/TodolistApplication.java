package com.mindhub.todolist;

import com.mindhub.todolist.model.*;
import com.mindhub.todolist.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class TodolistApplication {

    public static void main(String[] args) {

        SpringApplication.run(TodolistApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(CustomerEventRepository customerEventRepository, CustomerRepository customerRepository, EventRepository eventRepository, CommentRepository commentRepository, EventLocationRepository eventLocationRepository, LocationRepository locationRepository) {
        return args -> {

            Customer customer = new Customer(UUID.randomUUID(), "Roberto", "Salitre", "RS@example.com", true, "password789", (short) 35, Genero.MALE, Rol.USER);
            customerRepository.save(customer);

            Customer customer2 = new Customer(UUID.randomUUID(), "Matilda", "Capusoto", "mati.capu@example.com", true, "Lala123", (short) 30, Genero.FEMALE, Rol.ORGANIZER);
            customerRepository.save(customer2);

            Customer savedCustomer = customerRepository.findByEmail("RS@example.com").orElseThrow(() -> new RuntimeException("Customer not found"));

            Event event = new Event(UUID.randomUUID(), savedCustomer, "Evento asociado a RS", "event.jpg", (short) 30, "Evento 1");
            eventRepository.save(event);

            Event savedEvent = eventRepository.findById(event.getEvent_id())
                    .orElseThrow(() -> new RuntimeException("Event not found"));

            CustomerEvent customerEvent = new CustomerEvent(savedCustomer, savedEvent);
            customerEventRepository.save(customerEvent);

            Comment comment = new Comment(UUID.randomUUID(), savedCustomer, savedEvent, "Comentario evento RS");
            commentRepository.save(comment);

			/*Location location = new Location(UUID.randomUUID(), "Location Name", "123 Street, City", 100, "location.jpg");
			locationRepository.save(location);

			LocalDate fechaEventoPrueba = LocalDate.of(2025,1,1);
			EventLocation eventLocation = new EventLocation(event, location, fechaEventoPrueba, 50);
			eventLocationRepository.save(eventLocation);*/
        };
    }
}

