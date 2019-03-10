package beans.controllers;

import beans.models.User;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping
    public ModelAndView getUsersByName (@RequestParam String userName) {
        List<User> users = userService.getUsersByName(userName);
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("users", users);
        return mav;
    }
}
