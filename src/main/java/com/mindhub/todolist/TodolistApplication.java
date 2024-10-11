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

            Customer savedCustomer = customerRepository.findByEmail("RS@example.com").
                    orElseThrow(() -> new RuntimeException("Customer not found"));

            Customer customer1 = new Customer(UUID.randomUUID(), "María", "García", "maria.g@example.com", true, "clave123", (short) 28, Genero.FEMALE, Rol.USER);
            customerRepository.save(customer1);

            Customer customer11 = customerRepository.findByEmail("maria.g@example.com").
                    orElseThrow(() -> new RuntimeException("Customer not found"));

            Customer customer2 = new Customer(UUID.randomUUID(), "Matilda", "Capusoto", "mati.capu@example.com", true, "Lala123", (short) 30, Genero.FEMALE, Rol.ORGANIZER);
            customerRepository.save(customer2);

            Customer customer22 = customerRepository.findByEmail("mati.capu@example.com").
                    orElseThrow(() -> new RuntimeException("Customer not found"));

            Customer customer3 = new Customer(UUID.randomUUID(), "Sofía", "López", "sofia.lop@example.com", true, "p@ssw0rd", (short) 33, Genero.FEMALE, Rol.ORGANIZER);
            customerRepository.save(customer3);

            Customer customer33 = customerRepository.findByEmail("sofia.lop@example.com").
                    orElseThrow(() -> new RuntimeException("Customer not found"));

            Customer customer4 = new Customer(UUID.randomUUID(), "Juan", "Pérez", "juan.perez@example.com", true, "secure789", (short) 45, Genero.MALE, Rol.ORGANIZER);
            customerRepository.save(customer4);

            Customer customer44 = customerRepository.findByEmail("juan.perez@example.com").
                    orElseThrow(() -> new RuntimeException("Customer not found"));

            Customer customer5 = new Customer(UUID.randomUUID(), "Elena", "Rodríguez", "elena.r@example.com", true, "e12345", (short) 22, Genero.FEMALE, Rol.ADMIN);
            customerRepository.save(customer5);

            Customer customer55 = customerRepository.findByEmail("elena.r@example.com").
                    orElseThrow(() -> new RuntimeException("Customer not found"));

            Customer customer6 = new Customer(UUID.randomUUID(), "Carlos", "González", "c.gonzalez@example.com", true, "qwerty", (short) 29, Genero.MALE, Rol.ADMIN);
            customerRepository.save(customer6);

            Customer customer66 = customerRepository.findByEmail("c.gonzalez@example.com").
                    orElseThrow(() -> new RuntimeException("Customer not found"));

            Customer customer7 = new Customer(UUID.randomUUID(), "Laura", "Hernández", "laura.h@example.com", true, "contraseña", (short) 38, Genero.FEMALE, Rol.USER);
            customerRepository.save(customer7);

            Customer customer77 = customerRepository.findByEmail("c.gonzalez@example.com").
                    orElseThrow(() -> new RuntimeException("Customer not found"));

            Customer customer8 = new Customer(UUID.randomUUID(), "Pedro", "Sánchez", "pedrito@example.com", true, "123abc", (short) 42, Genero.MALE, Rol.USER);
            customerRepository.save(customer8);

            Customer customer88 = customerRepository.findByEmail("c.gonzalez@example.com").
                    orElseThrow(() -> new RuntimeException("Customer not found"));

            Customer customer9 = new Customer(UUID.randomUUID(), "Ana", "Jiménez", "ana.j@example.com", true, "a1b2c3", (short) 31, Genero.FEMALE, Rol.ORGANIZER);
            customerRepository.save(customer9);

            Customer customer99 = customerRepository.findByEmail("c.gonzalez@example.com").
                    orElseThrow(() -> new RuntimeException("Customer not found"));

            Customer customer10 = new Customer(UUID.randomUUID(), "Luis", "Martín", "luis.m@example.com", true, "martin123", (short) 37, Genero.MALE, Rol.ADMIN);
            customerRepository.save(customer10);

            Customer customer100 = customerRepository.findByEmail("c.gonzalez@example.com").
                    orElseThrow(() -> new RuntimeException("Customer not found"));



            /*EVENT*/
            Event event = new Event(null, savedCustomer, "Evento asociado a RS", "event.jpg", (short) 30, "Evento 1");
            eventRepository.save(event);

            Event savedEvent = eventRepository.findById(event.getEvent_id())
                    .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

            Event evento1 = new Event(null, savedCustomer, "Feria de comida local", "feria_comida.jpg", (short) 18, "Feria Gastronómica");
            eventRepository.save(evento1);

            Event savedEvento1 = eventRepository.findById(evento1.getEvent_id())
                    .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

            Event evento2 = new Event(null, savedCustomer, "Recital Rock", "recital_rock.jpg", (short) 16, "Concierto de Rock en Vivo");
            eventRepository.save(evento2);

            Event savedEvento2 = eventRepository.findById(evento2.getEvent_id())
                    .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

            Event evento3 = new Event(null, savedCustomer, "Competencia pizza libre", "maraton_pizza.jpg", (short) 12, "Maratón Anual de Pizza Libre");
            eventRepository.save(evento3);

            Event savedEvento3 = eventRepository.findById(evento3.getEvent_id())
                    .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

            Event evento4 = new Event(null, savedCustomer, "Taller de manualidades", "taller_manualidades.jpg", (short) 8, "Taller de Arte para Niños");
            eventRepository.save(evento4);

            Event savedEvento4 = eventRepository.findById(evento4.getEvent_id())
                    .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

            Event evento5 = new Event(null, savedCustomer, "Exposición de arte moderno", "expo_arte.jpg", (short) 21, "Exposición de Arte Contemporáneo");
            eventRepository.save(evento5);

            Event savedEvento5 = eventRepository.findById(evento5.getEvent_id())
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

            Comment comment1 = new Comment(UUID.randomUUID(), customer11, savedEvento1, "¡Excelente evento, lo disfruté mucho!");
            commentRepository.save(comment1);

            Comment comment2 = new Comment(UUID.randomUUID(), customer22, evento1, "La comida estaba deliciosa, definitivamente regresaré.");
            commentRepository.save(comment2);

            Comment comment3 = new Comment(UUID.randomUUID(), customer33, evento2, "Increíble concierto, la mejor música en vivo.");
            commentRepository.save(comment3);

            Comment comment4 = new Comment(UUID.randomUUID(), customer44, evento3, "¡Gané la competencia, la mejor pizza de la ciudad!");
            commentRepository.save(comment4);

            Comment comment5 = new Comment(UUID.randomUUID(), customer55, evento4, "Aprendí muchas técnicas nuevas, ¡gracias por la experiencia!");
            commentRepository.save(comment5);

            Comment comment6 = new Comment(UUID.randomUUID(), customer66, evento5, "Las obras de arte eran fascinantes, una exposición espectacular.");
            commentRepository.save(comment6);

            Comment comment7 = new Comment(UUID.randomUUID(), customer77, event, "¡Me encantó el evento, definitivamente volvería!");
            commentRepository.save(comment7);

            Comment comment8 = new Comment(UUID.randomUUID(), customer88, evento1, "Probé platos increíbles, un festival gastronómico genial.");
            commentRepository.save(comment8);

            Comment comment9 = new Comment(UUID.randomUUID(), customer99, evento2, "Una noche inolvidable, la banda fue increíble.");
            commentRepository.save(comment9);

            Comment comment10 = new Comment(UUID.randomUUID(), customer100, evento3, "Gran evento, disfruté cada rebanada de pizza.");
            commentRepository.save(comment10);

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

