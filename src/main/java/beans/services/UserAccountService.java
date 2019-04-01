package beans.services;

import beans.models.Ticket;
import beans.models.User;
import beans.models.UserAccount;

import java.util.List;

public interface UserAccountService {
    UserAccount create(UserAccount account);

    void delete(UserAccount account);

    UserAccount get(long id);

    UserAccount getByEmail(String email);

    List<UserAccount> getAll();

    UserAccount update(UserAccount account);

    Ticket bookTicket(User user, Ticket ticket);
}
