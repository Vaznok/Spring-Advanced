package beans.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler
    public ModelAndView handleMyException(Exception e) {
        ModelAndView mav = new ModelAndView("defaultError");
        mav.addObject("message", e.getMessage());
        return mav;
    }
}
