package beans.models;

import java.io.Serializable;
import java.util.Objects;

public class UserAccount implements Serializable {
    private long id;
    private User user;
    private Double cash;

    public UserAccount() {
    }

    public UserAccount(User user, Double cash) {
        this(0, user, cash);
    }

    public UserAccount(long id, User user, Double cash) {
        this.id = id;
        this.cash = cash;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return id == that.id &&
                user.equals(that.user) &&
                cash.equals(that.cash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, cash);
    }

}
