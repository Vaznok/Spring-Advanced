package beans.controllers;

import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(@Value("#{userServiceImpl}")UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(params = "name")
    public ModelAndView getUsersByName(@RequestParam String name) {
        ModelAndView mav = new ModelAndView("users");
        return mav.addObject("users", userService.getUsersByName(name));
    }

    @RequestMapping(params = "email")
    public ModelAndView getUserByEmail(@RequestParam String email) {
        ModelAndView mav = new ModelAndView("users");
        return mav.addObject("users", Arrays.asList(userService.getUserByEmail(email)));
    }

/*    @RequestMapping
    public ModelAndView getUsersByName (@RequestParam String userName) {
        List<User> users = userService.getUsersByName(userName);
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("users", users);
        return mav;
    }*/
}
