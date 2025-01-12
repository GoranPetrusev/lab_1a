package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EventListServlet", urlPatterns = "/eventListServlet")
public class EventListServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final EventService eventService;

    public EventListServlet(SpringTemplateEngine springTemplateEngine, EventService eventService) {
        this.springTemplateEngine = springTemplateEngine;
        this.eventService = eventService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String searchName = req.getParameter("searchName");
        String minRating = req.getParameter("minRating");
        List<Event> filteredEvents = eventService.listAll();

        if(searchName != null && !searchName.isEmpty() && minRating != null && !minRating.isEmpty()) {
            filteredEvents = filteredEvents.stream()
                    .filter(e -> e.getName().toLowerCase().contains(searchName.toLowerCase()) &&
                            e.getPopularityScore() >= Double.parseDouble(minRating))
                    .toList();
        } else if(searchName != null && !searchName.isEmpty()) {
            filteredEvents = filteredEvents.stream()
                    .filter(e -> e.getName().toLowerCase().contains(searchName.toLowerCase()))
                    .toList();
        } else if(minRating != null && !minRating.isEmpty()) {
            filteredEvents = filteredEvents.stream()
                    .filter(e -> e.getPopularityScore() >= Double.parseDouble(minRating))
                    .toList();
        }

        context.setVariable("events", filteredEvents);

        springTemplateEngine.process("listEvents.html", context, resp.getWriter());
    }
}
