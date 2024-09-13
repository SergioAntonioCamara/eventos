package com.mindhub.todolist;

import com.mindhub.todolist.model.*;
import com.mindhub.todolist.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
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

			Customer customer2 = new Customer(UUID.randomUUID(),"Matilda", "Capusoto", "mati.capu@example.com", true, "Lala123",(short) 30, Genero.FEMALE, Rol.ORGANIZER);
			customerRepository.save(customer2);

			Event event = new Event(UUID.randomUUID(), customer, "Event Description", "event.jpg", (short) 30, "Event Name");
			eventRepository.save(event);

			CustomerEvent customerEvent = new CustomerEvent(UUID.randomUUID(), customer, event);
			customerEventRepository.save(customerEvent);

			Comment comment = new Comment(UUID.randomUUID(), customer, event, "This is a test comment.");
			commentRepository.save(comment);

			Location location = new Location(UUID.randomUUID(), "Location Name", "123 Street, City", 100, "location.jpg");
			locationRepository.save(location);


			LocalDate fechaEventoPrueba = LocalDate.of(2025,1,1);
			EventLocation eventLocation = new EventLocation(event, location, fechaEventoPrueba, 50);
			eventLocationRepository.save(eventLocation);
		};
	}
}

