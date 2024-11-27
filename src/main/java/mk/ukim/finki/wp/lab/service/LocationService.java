package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    List<Location> findAll();
    Optional<Location> findById(Long id);

    void deleteById(Long id);

    void save(String name, String description, String address, String capacity, String locationId);

    void save(String name, String description, String address, String capacity);
}
