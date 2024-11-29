package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.exceptions.LocationNotFoundException;
import mk.ukim.finki.wp.lab.repository.jpa.EventRepository;
import mk.ukim.finki.wp.lab.repository.jpa.LocationRepository;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final EventRepository eventRepository;

    public LocationServiceImpl(LocationRepository locationRepository, EventRepository eventRepository) {
        this.locationRepository = locationRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Location> findAll() {
        return this.locationRepository.findAll();
    }

    @Override
    public Optional<Location> findById(Long id) {
        return this.locationRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.eventRepository.findAll().forEach(event -> {
            if(event.getLocation().getId().equals(id)) {
                this.eventRepository.delete(event);
            }
        });
        this.locationRepository.deleteById(id);
    }

    @Override
    public Optional<Location> save(String name, String description, String address, String capacity) {
        Location location = new Location(name, description, address, capacity);
        return Optional.of(this.locationRepository.save(location));
    }

    @Override
    public Optional<Location> update(Long id, String name, String description, String address, String capacity) {
        Location location = this.locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));

        location.setName(name);
        location.setDescription(description);
        location.setAddress(address);
        location.setCapacity(capacity);

        return Optional.of(this.locationRepository.save(location));
    }
}
