package beans.controllers;

import beans.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    private final SecurityService securityService;

    @Autowired
    public LoginController(@Value("#{securityServiceImpl}")SecurityService securityService) {
        this.securityService = securityService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout, HttpServletResponse response, HttpServletRequest request) {
        if(error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }
        if(logout != null) {
            securityService.logout(response, request);
            model.addAttribute("message", "Logged out successfully.");
        }
        return "login";
    }
}
