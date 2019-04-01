package beans.facade;

import beans.models.Auditorium;
import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;
import beans.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class BookingFacade {
    private final BookingService bookingService;
    private final UserAccountService userAccountService;
    private final UserService userService;
    private final EventService eventService;
    private final AuditoriumService auditoriumService;

    @Autowired
    public BookingFacade(@Value("#{bookingServiceImpl}")BookingService bookingService,
                         @Value("#{userAccountServiceImpl}") UserAccountService userAccountService,
                         @Value("#{userServiceImpl}") UserService userService,
                         @Value("#{eventServiceImpl}") EventService eventService,
                         @Value("#{auditoriumServiceImpl}") AuditoriumService auditoriumService) {
        this.bookingService = bookingService;
        this.userAccountService = userAccountService;
        this.userService = userService;
        this.eventService = eventService;
        this.auditoriumService = auditoriumService;
    }
    public Ticket bookTicket(String email, String eventName, List<Integer> seat, LocalDateTime dateTime, String auditoriumName) {
        Auditorium auditorium = auditoriumService.getByName(auditoriumName);
        User user = userService.getUserByEmail(email);
        Event event = eventService.getEvent(eventName, auditorium, dateTime);
        double ticketPrice = bookingService.getTicketPrice(eventName, auditoriumName, dateTime, seat, user);
        Ticket ticket = new Ticket(event, dateTime, seat, user, ticketPrice);
        return userAccountService.bookTicket(user, ticket);
    }
}
