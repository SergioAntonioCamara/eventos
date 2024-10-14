package com.mindhub.todolist.controller;

import com.mindhub.todolist.dto.EventCreationDTO;
import com.mindhub.todolist.dto.EventDTO;
import com.mindhub.todolist.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    //endpoint para todos los eventos
    @GetMapping
    public List<EventDTO> getAllEvents(){
        return eventService.getAllEvents();
    }

    //endpoint para buscar eventos por nombre
    @GetMapping("/search")
    public List<EventDTO> findEventsByName(@RequestParam String name){
        return eventService.findEventsByName(name);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ORGANIZER, ADMIN')")  // Solo ORGANIZER/admin puede crear eventos
    public ResponseEntity<String> createEvent(@Valid @RequestBody EventCreationDTO eventDTO) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String wantedOrganizer = authentication.getName();

            eventService.createEvent(eventDTO, wantedOrganizer);

            return ResponseEntity.status(HttpStatus.CREATED).body("Evento creado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
