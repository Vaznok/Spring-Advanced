package beans.models;

import beans.json.LocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 2/1/2016
 * Time: 7:35 PM
 */
public class User {
    private long      id;
    private String    email;
    private String    name;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthday;
    private String roles;
    private String password;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(11);

    public User() {
    }

    public User(long id, String email, String name, LocalDate birthday, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
        this.password = bCryptPasswordEncoder.encode(password);
        this.roles = UserRole.REGISTERED_USER.name();
    }

    public User(String email, String name, LocalDate birthday, String password) {
        this(-1, email, name, birthday, password);
    }

    public User withId(long id) {
        return new User(id, email, name, birthday, password);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void addRole(UserRole role) {
        if(!roles.contains(role.name())) {
            roles = roles + "," + role.name();
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        User user = (User) o;

        if (id != user.id)
            return false;
        if (email != null ? !email.equals(user.email) : user.email != null)
            return false;
        if (name != null ? !name.equals(user.name) : user.name != null)
            return false;
        return birthday != null ? birthday.equals(user.birthday) : user.birthday == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", email='" + email + '\'' +
               ", name='" + name + '\'' +
               ", birthday=" + birthday +
               '}';
    }
}
