package com.mindhub.todolist.service;

import com.mindhub.todolist.dto.LocationDTO;
import com.mindhub.todolist.model.Location;
import com.mindhub.todolist.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mindhub.todolist.exception.ResourceNotFoundException;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<LocationDTO> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream()
                .map(location -> new LocationDTO(
                        location.getLocation_id(),
                        location.getName(),
                        location.getLocation(),
                        location.getCapacity(),
                        location.getImg()))
                .collect(Collectors.toList());
    }

    public LocationDTO getLocationById(UUID locationId) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Locación no encontrada, ID: " + locationId));
        return new LocationDTO(location.getLocation_id(), location.getName(), location.getLocation(), location.getCapacity(), location.getImg());
    }

    public LocationDTO createLocation(LocationDTO locationDTO) {
        Location location = new Location();
        location.setName(locationDTO.getName());
        location.setLocation(locationDTO.getLocation());
        location.setCapacity(locationDTO.getCapacity());
        location.setImg(locationDTO.getImg());
        location = locationRepository.save(location);
        return new LocationDTO(location.getLocation_id(), location.getName(), location.getLocation(), location.getCapacity(), location.getImg());
    }

    public LocationDTO updateLocation(UUID locationId, LocationDTO locationDTO) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Locación no encontrada, ID: " + locationId));

        location.setName(locationDTO.getName());
        location.setLocation(locationDTO.getLocation());
        location.setCapacity(locationDTO.getCapacity());
        location.setImg(locationDTO.getImg());

        locationRepository.save(location);

        return new LocationDTO(location.getLocation_id(), location.getName(), location.getLocation(), location.getCapacity(), location.getImg());
    }

    public void deleteLocation(UUID locationId) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Locación no encontrada, ID: " + locationId));
        locationRepository.delete(location);
    }
}