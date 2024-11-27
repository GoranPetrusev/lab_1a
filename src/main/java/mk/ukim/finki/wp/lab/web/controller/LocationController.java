package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/locations")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping()
    public String getLocationsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Location> locations = locationService.findAll();
        model.addAttribute("locations", locations);

        return "listLocations";
    }

    @GetMapping("/edit-form/{id}")
    public String editEvent(@PathVariable Long id, Model model) {
        if(locationService.findById(id).isPresent()){
            Location loc = locationService.findById(id).get();
            model.addAttribute("location", loc);
            model.addAttribute("title", "Edit Location");
            model.addAttribute("form", "Edit Form");
            return "addLocation";
        }
        return "redirect:/locations?error=EventNotFound";
    }

    @GetMapping("/add-form")
    public String addEvent(Model model) {
        model.addAttribute("title", "Add Location");
        model.addAttribute("form", "Add Form");
        return "addLocation";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        locationService.deleteById(id);
        return "redirect:/locations";
    }

    @PostMapping("/add")
    public String saveEvent(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam String address,
                            @RequestParam String capacity,
                            @RequestParam String locationId) {

        if(locationId!=null && !locationId.isEmpty()) {
            locationService.save(name, address, capacity, description, locationId);
        } else {
            locationService.save(name, address, capacity, description);
        }

        return "redirect:/locations";
    }

}
