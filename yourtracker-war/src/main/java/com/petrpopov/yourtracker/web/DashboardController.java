package com.petrpopov.yourtracker.web;

import com.petrpopov.yourtracker.service.connection.CheckinService;
import com.petrpopov.yourtracker.service.mongo.CheckinStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.instagram.api.Instagram;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Map;

/**
 * User: petrpopov
 * Date: 17.02.13
 * Time: 19:38
 */
@Controller
public class DashboardController {

    @Autowired
    private Instagram instagram;

    @Autowired
    private CheckinService checkinService;

    @Autowired
    private CheckinStorageService checkinStorageService;


    @RequestMapping("/dashboard")
    public ModelAndView dashboard(Map<String, Object> model, Principal principal)
    {
        String username = principal.getName();

        //CheckinInfo info = checkinService.getAllCheckins();
        //checkinStorageService.save(info, username);


        return new ModelAndView("dashboard", model);
    }
}
