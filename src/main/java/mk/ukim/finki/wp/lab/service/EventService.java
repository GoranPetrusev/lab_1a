package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);
    Optional<Event> findById(Long id);
    void deleteById(Long id);
    void save(String name, String description, Double popularityScore, Location location);
    void save(String name, String description, Double popularityScore, Location location, Long eventId);
}