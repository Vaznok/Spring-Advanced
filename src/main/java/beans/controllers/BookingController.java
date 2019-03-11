package beans.controllers;

import beans.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller("/tickets")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(@Value("#{bookingServiceImpl}")BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RequestMapping
    public ModelAndView getTicketsForEvent(@RequestParam String event, @RequestParam String auditorium, @RequestParam String dateTime) {
        ModelAndView mav = new ModelAndView("tickets");
        return mav.addObject("tickets", bookingService.getTicketsForEvent(event, auditorium, LocalDateTime.parse(dateTime)));
    }

    @RequestMapping(produces = "application/pdf")
    public ModelAndView getTicketsForEventInPDF(@RequestParam String event, @RequestParam String auditorium, @RequestParam String dateTime) {
        ModelAndView mav = new ModelAndView("bookingPDF");
        return mav.addObject("tickets", bookingService.getTicketsForEvent(event, auditorium, LocalDateTime.parse(dateTime)));
    }
}
