package controllers;

import beans.models.Ticket;
import beans.services.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RequestMapping("/tickets")
    public ModelAndView getTicketsForEvent(@RequestParam String event, @RequestParam String auditorium) {
        List<Ticket> tickets = bookingService.getTicketsForEvent(event, auditorium, LocalDateTime.now());
        return null;
    }
}
