package beans;

import beans.aspects.CounterAspect;
import beans.aspects.DiscountAspect;
import beans.aspects.LuckyWinnerAspect;
import beans.models.*;
import beans.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

@Component
public class Bootstrap {
    private final static Logger logger = Logger.getLogger(Bootstrap.class.getSimpleName());

    @Autowired
    private ApplicationContext ctx;

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        AuditoriumService auditoriumService = (AuditoriumService) ctx.getBean("auditoriumServiceImpl");
        BookingService bookingService = (BookingService) ctx.getBean("bookingServiceImpl");
        EventService eventService = (EventService) ctx.getBean("eventServiceImpl");
        UserService userService = (UserService) ctx.getBean("userServiceImpl");
        DiscountService discountService = (DiscountService) ctx.getBean("discountServiceImpl");

        String email = "dmitriy.vbabichev@gmail.com";
        String name = "Dmytro Babichev";
        String eventName = "The revenant";
        String auditoriumName = "Blue hall";
        Auditorium blueHall = auditoriumService.getByName(auditoriumName);
        Auditorium yellowHall = auditoriumService.getByName("Yellow hall");
        Auditorium redHall = auditoriumService.getByName("Red hall");
        LocalDateTime dateOfEvent = LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(15, 45, 0));

        User userVetall = new User("vetall@gmail.com", "Vlasenko Vitalii", LocalDate.of(1990, 3, 14), "12345");
        userVetall.addRole(UserRole.BOOKING_MANAGER);
        userService.register(new User(email, name, LocalDate.now(), "12345"));
        userService.register(new User("laory@yandex.ru", name, LocalDate.of(1992, 4, 29), "12345"));
        userService.register(userVetall);

        User userByEmail = userService.getUserByEmail(email);
        logger.info("User with email: [" + email + "] is " + userByEmail);


        logger.info("All users with name: [" + name + "] are: ");
        userService.getUsersByName(name).forEach(System.out:: println);


        Event event1 = eventService.create(
                new Event(eventName, Rate.HIGH, 60, LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(9, 0, 0)),
                        blueHall));

        logger.info("Event by name: " + eventService.getByName(event1.getName()));

        eventService.create(new Event(eventName, Rate.HIGH, 60, dateOfEvent, blueHall));
        Event event2 = eventService.create(
                new Event(eventName, Rate.HIGH, 60, LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(21, 18, 0)),
                        blueHall));
        eventService.create(
                new Event(eventName, Rate.HIGH, 90, LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(21, 18, 0)),
                        redHall));
        Event event = new Event(eventName, Rate.HIGH, 150,
                LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(21, 18, 0)), yellowHall);
        event = eventService.create(event);

        logger.info("List of all events:");
        eventService.getAll().forEach(System.out:: println);


        logger.info(
                "Discount for user: [" + email + "] for event: [" + eventName + "] in auditorium: [" + auditoriumName +
                        "] on date: [" + dateOfEvent + "] is [" +
                        discountService.getDiscount(userByEmail, eventService.getEvent(eventName, blueHall, dateOfEvent))
                        + "]");


        eventService.remove(event2);
        logger.info("List of all events:");
        eventService.getAll().forEach(System.out:: println);


        List<Integer> seats = Arrays.asList(23, 24, 25, 26);
        double ticketPrice = bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(),
                event.getDateTime(), seats, userByEmail);
        logger.info("Price for event: [" + event + "], seats: [" + seats + "], user: [" + userByEmail + "] = "
                + ticketPrice);

        List<Integer> seats2 = Arrays.asList(27, 28, 29, 30);
        List<Integer> seats3 = Arrays.asList(2, 8, 9, 3);
        bookingService.bookTicket(userByEmail, new Ticket(event, LocalDateTime.now(), seats, userByEmail, ticketPrice));
        bookingService.bookTicket(userByEmail, new Ticket(event, LocalDateTime.now(), seats2, userByEmail,
                bookingService.getTicketPrice(event.getName(),
                        event.getAuditorium().getName(),
                        event.getDateTime(), seats2,
                        userByEmail)));
        bookingService.bookTicket(userByEmail, new Ticket(event, LocalDateTime.now(), seats3, userByEmail,
                bookingService.getTicketPrice(event.getName(),
                        event.getAuditorium().getName(),
                        event.getDateTime(), seats3,
                        userByEmail)));


        logger.info("Tickets booked for event: [" + event + "]");
        List<Ticket> ticketsForEvent = bookingService.getTicketsForEvent(event.getName(),
                event.getAuditorium().getName(),
                event.getDateTime());
        IntStream.range(0, ticketsForEvent.size()).forEach(
                i -> logger.info("" + i + ") " + ticketsForEvent.get(i)));


        eventService.getByName("testName1");
        eventService.getByName("testName2");
        eventService.getByName("testName2");
        eventService.getByName("testName3");
        eventService.getByName(eventName);
        eventService.getEvent(event.getName(), event.getAuditorium(), event.getDateTime());

        bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(), event.getDateTime(), seats,
                userByEmail);
        bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(), event.getDateTime(), seats,
                userByEmail);

        logger.info("CounterAspect.getAccessByNameStat() = " + CounterAspect.getAccessByNameStat());
        logger.info("CounterAspect.getGetPriceByNameStat() = " + CounterAspect.getGetPriceByNameStat());
        logger.info("CounterAspect.getBookTicketByNameStat() = " + CounterAspect.getBookTicketByNameStat());

        logger.info("DiscountAspect.getDiscountStatistics() = " + DiscountAspect.getDiscountStatistics());

        logger.info("LuckyWinnerAspect.getLuckyUsers() = " + LuckyWinnerAspect.getLuckyUsers());
    }

    @PostConstruct
    public void init() {
        AuditoriumService auditoriumService = (AuditoriumService) ctx.getBean("auditoriumServiceImpl");
        BookingService bookingService = (BookingService) ctx.getBean("bookingServiceImpl");
        EventService eventService = (EventService) ctx.getBean("eventServiceImpl");
        UserService userService = (UserService) ctx.getBean("userServiceImpl");
        DiscountService discountService = (DiscountService) ctx.getBean("discountServiceImpl");

        String email = "dmitriy.vbabichev@gmail.com";
        String name = "Dmytro Babichev";
        String eventName = "The revenant";
        String auditoriumName = "Blue hall";
        Auditorium blueHall = auditoriumService.getByName(auditoriumName);
        Auditorium yellowHall = auditoriumService.getByName("Yellow hall");
        Auditorium redHall = auditoriumService.getByName("Red hall");
        LocalDateTime dateOfEvent = LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(15, 45, 0));

        User userVetall = new User("vetall@gmail.com", "Vlasenko Vitalii", LocalDate.of(1990, 3, 14), "12345");
        userVetall.addRole(UserRole.BOOKING_MANAGER);
        userService.register(new User(email, name, LocalDate.now(), "12345"));
        userService.register(new User("laory@yandex.ru", name, LocalDate.of(1992, 4, 29), "12345"));
        userService.register(userVetall);

        User userByEmail = userService.getUserByEmail(email);
        logger.info("User with email: [" + email + "] is " + userByEmail);


        logger.info("All users with name: [" + name + "] are: ");
        userService.getUsersByName(name).forEach(System.out:: println);


        Event event1 = eventService.create(
                new Event(eventName, Rate.HIGH, 60, LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(9, 0, 0)),
                        blueHall));

        logger.info("Event by name: " + eventService.getByName(event1.getName()));

        eventService.create(new Event(eventName, Rate.HIGH, 60, dateOfEvent, blueHall));
        Event event2 = eventService.create(
                new Event(eventName, Rate.HIGH, 60, LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(21, 18, 0)),
                        blueHall));
        eventService.create(
                new Event(eventName, Rate.HIGH, 90, LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(21, 18, 0)),
                        redHall));
        Event event = new Event(eventName, Rate.HIGH, 150,
                LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(21, 18, 0)), yellowHall);
        event = eventService.create(event);

        logger.info("List of all events:");
        eventService.getAll().forEach(System.out:: println);


        logger.info(
                "Discount for user: [" + email + "] for event: [" + eventName + "] in auditorium: [" + auditoriumName +
                        "] on date: [" + dateOfEvent + "] is [" +
                        discountService.getDiscount(userByEmail, eventService.getEvent(eventName, blueHall, dateOfEvent))
                        + "]");


        eventService.remove(event2);
        logger.info("List of all events:");
        eventService.getAll().forEach(System.out:: println);


        List<Integer> seats = Arrays.asList(23, 24, 25, 26);
        double ticketPrice = bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(),
                event.getDateTime(), seats, userByEmail);
        logger.info("Price for event: [" + event + "], seats: [" + seats + "], user: [" + userByEmail + "] = "
                + ticketPrice);

        List<Integer> seats2 = Arrays.asList(27, 28, 29, 30);
        List<Integer> seats3 = Arrays.asList(2, 8, 9, 3);
        bookingService.bookTicket(userByEmail, new Ticket(event, LocalDateTime.now(), seats, userByEmail, ticketPrice));
        bookingService.bookTicket(userByEmail, new Ticket(event, LocalDateTime.now(), seats2, userByEmail,
                bookingService.getTicketPrice(event.getName(),
                        event.getAuditorium().getName(),
                        event.getDateTime(), seats2,
                        userByEmail)));
        bookingService.bookTicket(userByEmail, new Ticket(event, LocalDateTime.now(), seats3, userByEmail,
                bookingService.getTicketPrice(event.getName(),
                        event.getAuditorium().getName(),
                        event.getDateTime(), seats3,
                        userByEmail)));


        logger.info("Tickets booked for event: [" + event + "]");
        List<Ticket> ticketsForEvent = bookingService.getTicketsForEvent(event.getName(),
                event.getAuditorium().getName(),
                event.getDateTime());
        IntStream.range(0, ticketsForEvent.size()).forEach(
                i -> logger.info("" + i + ") " + ticketsForEvent.get(i)));


        eventService.getByName("testName1");
        eventService.getByName("testName2");
        eventService.getByName("testName2");
        eventService.getByName("testName3");
        eventService.getByName(eventName);
        eventService.getEvent(event.getName(), event.getAuditorium(), event.getDateTime());

        bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(), event.getDateTime(), seats,
                userByEmail);
        bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(), event.getDateTime(), seats,
                userByEmail);

        logger.info("CounterAspect.getAccessByNameStat() = " + CounterAspect.getAccessByNameStat());
        logger.info("CounterAspect.getGetPriceByNameStat() = " + CounterAspect.getGetPriceByNameStat());
        logger.info("CounterAspect.getBookTicketByNameStat() = " + CounterAspect.getBookTicketByNameStat());

        logger.info("DiscountAspect.getDiscountStatistics() = " + DiscountAspect.getDiscountStatistics());

        logger.info("LuckyWinnerAspect.getLuckyUsers() = " + LuckyWinnerAspect.getLuckyUsers());
    }
}
