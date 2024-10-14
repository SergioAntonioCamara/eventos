package com.mindhub.todolist.service;

import com.mindhub.todolist.dto.EventCreationDTO;
import com.mindhub.todolist.dto.EventDTO;
import com.mindhub.todolist.model.Customer;
import com.mindhub.todolist.model.Event;
import com.mindhub.todolist.model.EventLocation;
import com.mindhub.todolist.model.Location;
import com.mindhub.todolist.repository.CustomerRepository;
import com.mindhub.todolist.repository.EventLocationRepository;
import com.mindhub.todolist.repository.EventRepository;
import com.mindhub.todolist.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventLocationRepository eventLocationRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private EventLogService eventLogService;

    @Autowired
    private CustomerRepository customerRepository;


    // Método para obtener todos los eventos como DTO
    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Método para encontrar eventos por su nombre
    public List<EventDTO> findEventsByName(String name) {
        return eventRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Método para convertir Event en EventDTO
    private EventDTO convertToDTO(Event event) {
        return new EventDTO(
                event.getEvent_id(),
                event.getName(),
                event.getDesc(),
                event.getImg(),
                event.getAge_req()
                //event.getEventLocations().get(0).getDate().toString(), // Asumimos que tiene al menos una ubicación
                //event.getEventLocations().get(0).getLocation().getName(),
                //event.getEventLocations().get(0).getAssistance()
        );
    }

    public void createEvent(EventCreationDTO eventCreationDTO, String wantedOrganizer){
        //verifica que la ubicación existe:
        Location location = locationRepository.findById(eventCreationDTO.getEventLocation_id())
                .orElseThrow(() -> new IllegalArgumentException("Ubicación no encontrada"));

        //verifica que el organizador exista:
        Customer organizer = customerRepository.findByEmail(wantedOrganizer)
                .orElseThrow(() -> new IllegalArgumentException("Organizador no encontrado"));

        // captura el usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //String organizer = authentication.getName();  // trae el dato del organizador

        // instancia de EventLocation y asignación de fecha
        EventLocation eventLocation = new EventLocation();
        eventLocation.setDate(eventCreationDTO.getDate());
        eventLocation.setLocation(location);

        // instancia de Event
        Event event = new Event();
        event.setName(eventCreationDTO.getName());
        event.setDesc(eventCreationDTO.getDesc());
        event.setOrganizer(organizer);

        //tengo List en Event: private List<EventLocation> eventLocations = new ArrayList<>();
        event.setEventLocations(Collections.singletonList(eventLocation)); //relación con EventLocation

        // dejar registrado en logs la creación del evento
        eventLogService.logEventCreation(event.getName(), wantedOrganizer);

        eventRepository.save(event);
    }
}
