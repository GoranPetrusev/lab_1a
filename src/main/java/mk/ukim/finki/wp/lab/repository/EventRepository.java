package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository {
    public List<Event> findAll() {
        return DataHolder.events;
    }

    public List<Event> searchEvents(String text) {
        return DataHolder.events.stream()
                .filter(e -> e.getName().toLowerCase().contains(text.toLowerCase()) ||
                             e.getDescription().toLowerCase().contains(text.toLowerCase()))
                .toList();
    }

    public Optional<Event> findById(Long id) {
       return DataHolder.events.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public void deleteById(Long id) {
        DataHolder.events.removeIf(e -> e.getId().equals(id));
    }

    public void save(String name, String description, Double popularityScore, Location location, Long eventId) {
        Event event = DataHolder.events.stream().filter(e -> e.getId().equals(eventId)).findFirst().orElse(null);

        event.setName(name);
        event.setDescription(description);
        event.setPopularityScore(popularityScore);
        event.setLocation(location);
    }

    public void save(String name, String description, Double popularityScore, Location location) {
        DataHolder.events.add(new Event(name, description, popularityScore, location));
    }
}
