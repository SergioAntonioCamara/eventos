package com.mindhub.todolist.service;

import com.mindhub.todolist.dto.EventDTO;
import com.mindhub.todolist.model.Event;
import com.mindhub.todolist.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

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
}
