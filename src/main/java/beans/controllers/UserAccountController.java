package beans.controllers;

import beans.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
public class UserAccountController {
    private final UserAccountService userAccountService;

    @Autowired
    public UserAccountController(@Qualifier("userAccountServiceImpl") UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @RequestMapping(value = "/account")
    public ModelAndView getAll() {
        return new ModelAndView("accounts")
                .addObject("accounts", userAccountService.getAll());
    }

    @RequestMapping(value = "/account", params = "email")
    public ModelAndView getUserAccount(@RequestParam String email) {
        return new ModelAndView("accounts")
                .addObject("accounts", Arrays.asList(userAccountService.getByEmail(email)));
    }
}
