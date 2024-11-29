package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.jpa.EventRepository;
import mk.ukim.finki.wp.lab.repository.jpa.LocationRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataHolder {
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public DataHolder(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @PostConstruct
    public void init() {
        List<Location> locations = new ArrayList<>();
        if(this.locationRepository.count() == 0) {
            locations.add(new Location("Green Park Stadium", "123 Park Lane, Cityville", "50,000", "A large outdoor stadium for sports events and concerts."));
            locations.add(new Location("Central Library", "456 Main St, Townsville", "500", "A public library offering a vast collection of books and digital resources."));
            locations.add(new Location("City Conference Center", "789 Business Blvd, Metro City", "2000", "A modern venue for conferences, meetings, and exhibitions."));
            locations.add(new Location("Sunny Beach Resort", "101 Beach Rd, Seaside Town", "300", "A luxury resort offering beachfront accommodations and recreational activities."));
            locations.add(new Location("Mountain View Hotel", "202 Mountain Peak Rd, Highpoint", "150", "A hotel located at the top of the mountain with breathtaking views and hiking trails."));

            this.locationRepository.saveAll(locations);
        }

        List<Event> events = new ArrayList<>();
        if(this.eventRepository.count() == 0) {
            events.add(new Event("Concert", "A live musical performance", 8.5));
            events.add(new Event("Conference", "An event for professionals to discuss trends", 7.2));
            events.add(new Event("Workshop", "An interactive training session", 9.0));
            events.add(new Event("Webinar", "A seminar conducted over the web", 6.8));
            events.add(new Event("Meetup", "A casual gathering for people with shared interests", 7.5));
            events.add(new Event("Festival", "A celebration of arts and culture", 9.5));
            events.add(new Event("Party", "A social gathering for fun and entertainment", 8.0));
            events.add(new Event("Exhibition", "A display of artistic or educational works", 7.8));
            events.add(new Event("Seminar", "A session for discussion and training", 6.5));
            events.add(new Event("Trade Show", "An exhibition for businesses to showcase their products", 7.0));

            Random rnd = new Random();
            events.forEach(event -> {
                int ind = rnd.nextInt(this.locationRepository.findAll().size());
                Location rndLocation = this.locationRepository.findAll().get(ind);
                event.setLocation(rndLocation);
            });

            this.eventRepository.saveAll(events);
        }
    }
}
