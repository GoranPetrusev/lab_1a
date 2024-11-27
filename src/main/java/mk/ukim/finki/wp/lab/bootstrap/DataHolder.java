package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events = null;
    public static List<String> activeSessions = null;
    public static List<Location> locations = null;

    @PostConstruct
    public void init() {
        activeSessions = new ArrayList<>();

        locations = new ArrayList<>();
        locations.add(new Location("Green Park Stadium", "123 Park Lane, Cityville", "50,000", "A large outdoor stadium for sports events and concerts."));
        locations.add(new Location("Central Library", "456 Main St, Townsville", "500", "A public library offering a vast collection of books and digital resources."));
        locations.add(new Location("City Conference Center", "789 Business Blvd, Metro City", "2000", "A modern venue for conferences, meetings, and exhibitions."));
        locations.add(new Location("Sunny Beach Resort", "101 Beach Rd, Seaside Town", "300", "A luxury resort offering beachfront accommodations and recreational activities."));
        locations.add(new Location("Mountain View Hotel", "202 Mountain Peak Rd, Highpoint", "150", "A hotel located at the top of the mountain with breathtaking views and hiking trails."));

        events = new ArrayList<>();
        events.add(new Event("Concert", "A live musical performance", 8.5, locations.get((int)(Math.random() * locations.size()))));
        events.add(new Event("Conference", "An event for professionals to discuss trends", 7.2, locations.get((int)(Math.random() * locations.size()))));
        events.add(new Event("Workshop", "An interactive training session", 9.0, locations.get((int)(Math.random() * locations.size()))));
        events.add(new Event("Webinar", "A seminar conducted over the web", 6.8, locations.get((int)(Math.random() * locations.size()))));
        events.add(new Event("Meetup", "A casual gathering for people with shared interests", 7.5, locations.get((int)(Math.random() * locations.size()))));
        events.add(new Event("Festival", "A celebration of arts and culture", 9.5, locations.get((int)(Math.random() * locations.size()))));
        events.add(new Event("Party", "A social gathering for fun and entertainment", 8.0, locations.get((int)(Math.random() * locations.size()))));
        events.add(new Event("Exhibition", "A display of artistic or educational works", 7.8, locations.get((int)(Math.random() * locations.size()))));
        events.add(new Event("Seminar", "A session for discussion and training", 6.5, locations.get((int)(Math.random() * locations.size()))));
        events.add(new Event("Trade Show", "An exhibition for businesses to showcase their products", 7.0, locations.get((int)(Math.random() * locations.size()))));
    }
}
