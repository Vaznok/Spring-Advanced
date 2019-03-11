package beans.controllers;

import beans.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(@Value("#{eventServiceImpl}") EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "/event")
    public String getAllEvents(Model model) {
        model.addAttribute("events", eventService.getAll());
        return "events";
    }

    @RequestMapping(value = "/event/{name}")
    public String getEventByName(Model model, @PathVariable String name) {
        model.addAttribute("events", eventService.getByName(name));
        return "events";
    }
}
