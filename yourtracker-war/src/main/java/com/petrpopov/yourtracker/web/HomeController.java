package com.petrpopov.yourtracker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * User: petrpopov
 * Date: 11.02.13
 * Time: 22:29
 */

@Controller
public class HomeController {

    @RequestMapping({"/","/home", "/index", "/main"})
    public String showHomePage(@CookieValue(required = false, value = "guid") String cookie,
                               Map<String, Object> model)
    {
        return "home";
    }
}
