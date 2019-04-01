package beans.controllers;

import beans.facade.BookingFacade;
import beans.form.BookingForm;
import beans.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Controller("/tickets")
public class BookingController {

    private final BookingService bookingService;
    private final BookingFacade bookingFacade;

    @Autowired
    public BookingController(@Value("#{bookingServiceImpl}")BookingService bookingService,
                             @Value("#{bookingFacade}") BookingFacade bookingFacade) {
        this.bookingService = bookingService;
        this.bookingFacade = bookingFacade;
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

    @RequestMapping("/book")
    public String showBookingPage() {
        return "booking";
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ModelAndView bookTicket(HttpServletRequest request, @ModelAttribute("bookingForm") BookingForm form) {
        String email = request.getUserPrincipal().getName();
        List<Integer> seat = Collections.singletonList(form.getSeat());
        bookingFacade.bookTicket(email, form.getEventName(), seat, LocalDateTime.parse(form.getDateTime()), form.getAuditoriumName());
        return new ModelAndView("booking-result");
    }
}
