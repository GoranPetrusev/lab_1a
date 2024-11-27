package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/eventBooking")
public class EventBookingController {
    private final EventBookingService eventBookingService;

    public EventBookingController(EventBookingService eventBookingService) {
        this.eventBookingService = eventBookingService;
    }

    @PostMapping()
    public String getEventBookingPage(@RequestParam(required = false) String error,
                                      @RequestParam String selectedEvent,
                                      @RequestParam int numTickets,
                                      Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        EventBooking booking = eventBookingService.placeBooking(selectedEvent, " ", " ", numTickets);

        model.addAttribute("booking", booking);

        return "bookingConfirmation";
    }
}
