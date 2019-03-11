package beans.controllers;

import beans.models.Auditorium;
import beans.services.AuditoriumService;
import beans.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
public class AuditoriumController {

    private final AuditoriumService auditoriumService;

    @Autowired
    public AuditoriumController(@Value("#{auditoriumServiceImpl}") AuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }

    @RequestMapping(value = "/auditorium")
    public String getAllEvents(Model model) {
        model.addAttribute("auditoriums", auditoriumService.getAuditoriums());
        return "auditoriums";
    }

    @RequestMapping(value = "/auditorium/{name}")
    public String getEventByName(Model model, @PathVariable String name) {
        model.addAttribute("auditoriums", Arrays.asList(auditoriumService.getByName(name)));
        return "auditoriums";
    }
}
