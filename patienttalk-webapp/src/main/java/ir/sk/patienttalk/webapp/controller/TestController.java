package ir.sk.patienttalk.webapp.controller;

import ir.sk.patienttalk.webapp.dto.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/7/2017
 */
@Controller
public class TestController {

    @RequestMapping(value="/testui", method = RequestMethod.GET)
    public String test(@ModelAttribute("test") Test test) {
     //   ModelAndView modelAndView = new ModelAndView("testui");
     //   modelAndView.addObject(new Test());

        return "testui";
    }
}
