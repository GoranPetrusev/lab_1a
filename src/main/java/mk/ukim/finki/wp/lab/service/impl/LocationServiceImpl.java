package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.inmemory.InMemoryLocationRepository;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    private final InMemoryLocationRepository inMemoryLocationRepository;

    public LocationServiceImpl(InMemoryLocationRepository inMemoryLocationRepository) {
        this.inMemoryLocationRepository = inMemoryLocationRepository;
    }

    @Override
    public List<Location> findAll() {
        return inMemoryLocationRepository.findAll();
    }

    @Override
    public Optional<Location> findById(Long id) {
        return inMemoryLocationRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        inMemoryLocationRepository.deleteById(id);
    }

    @Override
    public void save(String name, String description, String address, String capacity, String locationId) {
        inMemoryLocationRepository.save(name, address, capacity, description, locationId);
    }

    @Override
    public void save(String name, String description, String address, String capacity) {
        inMemoryLocationRepository.save(name, address, capacity, description);
    }
}
