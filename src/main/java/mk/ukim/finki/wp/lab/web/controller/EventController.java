package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.service.EventService;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;

    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping()
    public String getEventsPage(@RequestParam(required = false) String error,
                                @RequestParam(required = false) String searchName,
                                @RequestParam(required = false) String minRating,
                                Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Event> filteredEvents = eventService.listAll();

        if(searchName != null && !searchName.isEmpty() && minRating != null && !minRating.isEmpty()) {
            filteredEvents = filteredEvents.stream()
                    .filter(e -> e.getName().toLowerCase().contains(searchName.toLowerCase()) &&
                            e.getPopularityScore() >= Double.parseDouble(minRating))
                    .toList();
        } else if(searchName != null && !searchName.isEmpty()) {
            filteredEvents = eventService.searchEvents(searchName);
        } else if(minRating != null && !minRating.isEmpty()) {
            filteredEvents = filteredEvents.stream()
                    .filter(e -> e.getPopularityScore() >= Double.parseDouble(minRating))
                    .toList();
        }


        model.addAttribute("events", filteredEvents);
        return "listEvents";
    }

    @GetMapping("/edit-form/{id}")
    public String editEvent(@PathVariable Long id, Model model) {
        if(eventService.findById(id).isPresent()){
            List<Location> locations = locationService.findAll();
            Event e = eventService.findById(id).get();
            model.addAttribute("event", e);
            model.addAttribute("locations", locations);
            model.addAttribute("title", "Edit Event");
            model.addAttribute("form", "Edit Form");
            return "addEvent";
        }
        return "redirect:/events?error=EventNotFound";
    }

    @GetMapping("/add-form")
    public String addEvent(Model model) {
        List<Location> locations = locationService.findAll();
        model.addAttribute("locations", locations);
        model.addAttribute("title", "Add Event");
        model.addAttribute("form", "Add Form");
        return "addEvent";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteById(id);
        return "redirect:/events";
    }

    @PostMapping("/add")
    public String saveEvent(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam Double popularityScore,
                            @RequestParam String locationId,
                            @RequestParam String eventId) {
        Location location = locationService.findById(Long.valueOf(locationId)).orElse(null);

        if(eventId != null && !eventId.isEmpty()) {
            eventService.save(name, description, popularityScore, location, Long.valueOf(eventId));
        } else {
            eventService.save(name, description, popularityScore, location);
        }

        return "redirect:/events";
    }
}
