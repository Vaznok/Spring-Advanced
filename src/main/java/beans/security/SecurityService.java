package beans.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SecurityService {
    String findLoggedInUserName();
    void logout(HttpServletResponse response, HttpServletRequest request);
}
