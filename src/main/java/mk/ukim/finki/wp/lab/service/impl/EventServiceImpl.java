package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.exceptions.EventNotFoundException;
import mk.ukim.finki.wp.lab.model.exceptions.LocationNotFoundException;
import mk.ukim.finki.wp.lab.repository.jpa.EventRepository;
import mk.ukim.finki.wp.lab.repository.jpa.LocationRepository;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Event> listAll() {
        return this.eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String name, String rating, String locationId) {
        name = name == null ? "" : name;
        rating = rating == null || rating.isEmpty() ? "0.0" : rating;

        if(locationId == null || locationId.isEmpty())
            return this.eventRepository.findAllByNameContainingIgnoreCaseAndPopularityScoreGreaterThanEqual(name, Double.parseDouble(rating));

        return this.eventRepository.findAllByNameContainingIgnoreCaseAndPopularityScoreGreaterThanEqualAndLocationId(name, Double.parseDouble(rating), Long.valueOf(locationId));
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> listByLocationId(Long locationId) {
        return this.eventRepository.findAllByLocationId(locationId);
    }

    @Override
    public void deleteById(Long id) {
        this.eventRepository.deleteById(id);
    }

    @Override
    public Optional<Event> save(String name, String description, Double popularityScore, Long locationId) {
        Location location = this.locationRepository.findById(locationId).orElseThrow(
                () -> new LocationNotFoundException(locationId));

        Event event = new Event(name, description, popularityScore, location);

        return Optional.of(this.eventRepository.save(event));
    }

    @Override
    public Optional<Event> update(Long id, String name, String description, Double popularityScore, Long locationId) {
        Event event = this.eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
        Location location = this.locationRepository.findById(locationId)
                .orElseThrow(() -> new LocationNotFoundException(locationId));

        event.setName(name);
        event.setDescription(description);
        event.setPopularityScore(popularityScore);
        event.setLocation(location);

        return Optional.of(this.eventRepository.save(event));
    }
}
