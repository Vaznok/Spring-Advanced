package beans.security;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service("securityServiceImpl")
public class SecurityServiceImpl implements SecurityService {

    @Override
    public String findLoggedInUserName() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails instanceof MyUserDetails) {
            return ((MyUserDetails)userDetails).getFullName();
        }
        return null;
    }

    @Override
    public void logout(HttpServletResponse response, HttpServletRequest request) {
        String cookieName = "remember-me";
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        cookie.setPath(StringUtils.hasLength(request.getContextPath()) ? request.getContextPath() : "/");
        response.addCookie(cookie);
    }
}
