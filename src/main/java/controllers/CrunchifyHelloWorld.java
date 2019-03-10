package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
 * author: Crunchify.com
 *
 */

@Controller
public class CrunchifyHelloWorld {

    @RequestMapping("/welcome")
    public String helloWorld() {
        return "welcome";
    }
}