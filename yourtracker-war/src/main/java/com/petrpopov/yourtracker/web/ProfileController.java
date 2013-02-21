package com.petrpopov.yourtracker.web;

import com.petrpopov.yourtracker.service.CheckinEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.foursquare.api.Foursquare;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * User: petrpopov
 * Date: 17.02.13
 * Time: 19:38
 */
@Controller
public class ProfileController {

    @Autowired
    private Foursquare api;

    @Autowired
    private CheckinEntityService checkinEntityService;

    @RequestMapping("/dashboard")
    public ModelAndView dashboard(Map<String, Object> model)
    {
        return new ModelAndView("dashboard", model);
    }
}
