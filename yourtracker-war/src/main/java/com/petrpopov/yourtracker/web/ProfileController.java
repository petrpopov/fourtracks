package com.petrpopov.yourtracker.web;

import fi.foyt.foursquare.api.FoursquareApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.foursquare.api.Checkin;
import org.springframework.social.foursquare.api.CheckinInfo;
import org.springframework.social.foursquare.api.Foursquare;
import org.springframework.social.foursquare.api.FoursquareUser;
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

    @RequestMapping("/profile")
    public ModelAndView profile(Map<String, Object> model)
    {
        api.userOperations().getCheckinsByApi("self");
        CheckinInfo checkins = api.userOperations().getCheckins();

/*        System.out.println(checkins.getTotal());

        int i = 0;
        for(Checkin checkin : checkins.getCheckins()) {
            System.out.println(checkin);
            if( ++i == 10 )
                break;
        }
                                                     */


        return new ModelAndView("profile", model);
    }
}
