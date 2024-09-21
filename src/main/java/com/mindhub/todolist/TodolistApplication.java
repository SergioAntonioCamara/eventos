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

            /*CUSTOMER*/
            Customer customer = new Customer(UUID.randomUUID(), "Roberto", "Salitre", "RS@example.com", true, "password789", (short) 35, Genero.MALE, Rol.USER);
            customerRepository.save(customer);

            Customer customer2 = new Customer(UUID.randomUUID(), "Matilda", "Capusoto", "mati.capu@example.com", true, "Lala123", (short) 30, Genero.FEMALE, Rol.ORGANIZER);
            customerRepository.save(customer2);

            Customer savedCustomer = customerRepository.findByEmail("RS@example.com").
                    orElseThrow(() -> new RuntimeException("Customer not found"));

            /*EVENT*/
            Event event = new Event(null, savedCustomer, "Evento asociado a RS", "event.jpg", (short) 30, "Evento 1");
            eventRepository.save(event);

            Event savedEvent = eventRepository.findById(event.getEvent_id())
                    .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

            System.out.println("Event ID: " + event.getEvent_id());

            /*LOCATION*/
            Location location1 = new Location(null, "Quieres ser tu propio jefe", "ASV", 500, "image1.jpg");
            locationRepository.save(location1);

            Location savedLocation1 = locationRepository.findById(location1.getLocation_id())
                    .orElseThrow(() -> new RuntimeException("Location no encontrada"));

            /*FECHA*/
            LocalDate fechaEventoPrueba = LocalDate.of(2025,1,1);



            /*COMMENT*/
            Comment comment = new Comment(null, savedCustomer, savedEvent, "Comentario evento RS");
            commentRepository.save(comment);

            Comment savedComment = commentRepository.findById(comment.getComment_id())
                    .orElseThrow(()-> new RuntimeException("Sin comentarios"));

            /*EVENTLOCATION*/

            /* ********** esta parte del código no me funcionó y tuve que hacer la siguiente.
            EventLocation eventLocation1 = new EventLocation(savedEvent, savedLocation1, fechaEventoPrueba,369);
            eventLocationRepository.save(eventLocation1);

            EventLocation savedEventLocation = eventLocationRepository.findById(eventLocation1.getEventLocation_id())
                    .orElseThrow(() -> new RuntimeException("EventLocation no encontrado!"));****/

            EventLocation eventLocation1 = new EventLocation(savedEvent, savedLocation1, fechaEventoPrueba, 369);
            EventLocation savedEventLocation = eventLocationRepository.save(eventLocation1);  // Guarda y obtén el objeto con ID

            // El ID ya debería estar disponible en savedEventLocation
            UUID eventLocationId = savedEventLocation.getEventLocation_id();  // Obtén el ID generado

            // Ahora busca usando el ID ya generado
            EventLocation foundEventLocation = eventLocationRepository.findById(eventLocationId)
                    .orElseThrow(() -> new RuntimeException("EventLocation no encontrado!"));

            System.out.println("EventLocation encontrado con ID: " + foundEventLocation.getEventLocation_id());


            /*CUSTOMEREVENT*/
            /* no funciona
            CustomerEvent customerEvent = new CustomerEvent(savedCustomer, eventLocation1);
            customerEventRepository.save(customerEvent); */

            /* CUSTOMER EVENT */
            // Creamos el CustomerEvent con el Customer y el EventLocation que ya tienes guardados
            CustomerEvent customerEvent1 = new CustomerEvent(savedCustomer, savedEventLocation);

            // Guardamos el CustomerEvent para generar el ID
            CustomerEvent savedCustomerEvent = customerEventRepository.save(customerEvent1);  // Guarda y obtén el objeto con ID generado

            // Obtenemos el ID generado para el CustomerEvent
            UUID customerEventId = savedCustomerEvent.getUser_id();

            // Ahora, si necesitas buscar el CustomerEvent por su ID, puedes hacerlo
            CustomerEvent foundCustomerEvent = customerEventRepository.findById(customerEventId)
                    .orElseThrow(() -> new RuntimeException("CustomerEvent no encontrado!"));

            System.out.println("CustomerEvent encontrado con ID: " + foundCustomerEvent.getUser_id());


        };
    }
}

