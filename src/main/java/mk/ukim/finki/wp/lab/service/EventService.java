package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String name, String rating);
    Optional<Event> findById(Long id);
    void deleteById(Long id);
    Optional<Event> save(String name, String description, Double popularityScore, Long locationId);
    Optional<Event> update(Long id, String name, String description, Double popularityScore, Long locationId);
}