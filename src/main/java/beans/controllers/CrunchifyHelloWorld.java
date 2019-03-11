package beans.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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