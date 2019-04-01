package beans.daos.db;

import beans.daos.AbstractDAO;
import beans.daos.UserAccountDAO;
import beans.models.UserAccount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "userAccountDAO")
public class UserAccountDAOImpl extends AbstractDAO implements UserAccountDAO {
    @Override
    public UserAccount get(long id) {
        return getCurrentSession().get(UserAccount.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserAccount> getAll() {
        return ((List<UserAccount>) createBlankCriteria(UserAccount.class).list());
    }

    @Override
    public UserAccount create(UserAccount account) {
        long id = (Long) getCurrentSession().save(account);
        return get(id);
    }

    @Override
    public void delete(UserAccount account) {
        getCurrentSession().delete(account);
    }

    @Override
    public UserAccount update(UserAccount account) {
        return ((UserAccount) getCurrentSession().merge(account));
    }
}
