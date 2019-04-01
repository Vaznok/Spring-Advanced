package beans.services;

import beans.daos.UserAccountDAO;
import beans.models.Ticket;
import beans.models.User;
import beans.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("userAccountServiceImpl")
@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountDAO userAccountDAO;
    private final UserService userService;
    private final BookingService bookingService;

    @Autowired
    public UserAccountServiceImpl(@Qualifier("userAccountDAO")UserAccountDAO userAccountDAO,
                                  @Qualifier("userServiceImpl")UserService userService,
                                  @Qualifier("bookingServiceImpl")BookingService bookingService) {
        this.userAccountDAO = userAccountDAO;
        this.userService = userService;
        this.bookingService = bookingService;
    }

    @Override
    public UserAccount create(UserAccount account) {
        return userAccountDAO.create(account);
    }

    @Override
    public void delete(UserAccount account) {
        userAccountDAO.delete(account);
    }

    @Override
    public UserAccount get(long id) {
        return userAccountDAO.get(id);
    }

    /*
        This method doesn't break transaction because it uses method from "UserService"
        and "UserAccount" model strongly bounded with "User" model (one-to-one relationship)
    */
    @Override
    public UserAccount getByEmail(String email) {
        long userId = userService.getUserByEmail(email).getId();
        Optional<UserAccount> userAccountOpt = getAll()
                .stream()
                .filter(userAccount -> userAccount.getUser().getId() == userId)
                .findFirst();
        return userAccountOpt.orElse(null);
    }

    @Override
    public List<UserAccount> getAll() {
        return userAccountDAO.getAll();
    }

    @Override
    public UserAccount update(UserAccount account) {
        return userAccountDAO.update(account);
    }

    @Override
    public Ticket bookTicket(User user, Ticket ticket) {
        double balance = getByEmail(user.getEmail()).getCash() - ticket.getPrice();
        if (balance >= 0) {
            UserAccount account = get(user.getId());
            account.setCash(balance);
            userAccountDAO.update(account);
            return bookingService.bookTicket(user, ticket);
        } else {
            throw new IllegalStateException("Unable to book ticket: Not enough cash on your account. Missing funds: " + Math.abs(balance));
        }
    }
}
