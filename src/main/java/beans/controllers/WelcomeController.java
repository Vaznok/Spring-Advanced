package beans.controllers;

import beans.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {
    private final SecurityService securityService;

    @Autowired
    public WelcomeController(@Value("#{securityServiceImpl}")SecurityService securityService) {
        this.securityService = securityService;
    }

    @RequestMapping("/")
    public ModelAndView showWelcomePage() {
        ModelAndView mav = new ModelAndView("welcome");
        String fullName = securityService.findLoggedInUserName();
        if(fullName == null) {
            return mav.addObject("username", "guest");
        } else {
            return mav.addObject("username", fullName);
        }
    }
}