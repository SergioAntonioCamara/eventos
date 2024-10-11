package com.mindhub.todolist.controller;

import com.mindhub.todolist.dto.EventDTO;
import com.mindhub.todolist.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}
