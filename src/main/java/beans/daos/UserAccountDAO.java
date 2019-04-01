package beans.daos;

import beans.models.UserAccount;

import java.util.List;

public interface UserAccountDAO {

    UserAccount get(long id);

    List<UserAccount> getAll();

    UserAccount create(UserAccount account);

    void delete(UserAccount account);

    UserAccount update(UserAccount account);
}
