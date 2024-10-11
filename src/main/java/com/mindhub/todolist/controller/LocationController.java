package com.mindhub.todolist.controller;

import com.mindhub.todolist.dto.LocationDTO;
import com.mindhub.todolist.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    // Obtener todas las ubicaciones (para visitantes y usuarios)
    @GetMapping
    public List<LocationDTO> getAllLocations() {
        return locationService.getAllLocations();
    }

    // Obtener ubicación por ID (para usuarios)
    @GetMapping("/{id}")
    public LocationDTO getLocationById(@PathVariable UUID id) {
        return locationService.getLocationById(id);
    }

    // que un organizador pueda crear una ubicación
    @PostMapping
    @PreAuthorize("hasRole('ORGANIZER')")
    public ResponseEntity<LocationDTO> createLocation(@RequestBody LocationDTO locationDTO) {
        LocationDTO newLocation = locationService.createLocation(locationDTO);
        return new ResponseEntity<>(newLocation, HttpStatus.CREATED);
    }

    // que un organizador pueda editar la ubicación
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ORGANIZER')")
    public LocationDTO updateLocation(@PathVariable UUID id, @RequestBody LocationDTO locationDTO) {
        return locationService.updateLocation(id, locationDTO);
    }

    // que un organizador pueda eliminar la ubicación
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteLocation(@PathVariable UUID id) {
        locationService.deleteLocation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
