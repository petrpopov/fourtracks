package com.petrpopov.yourtracker.web;

import com.petrpopov.yourtracker.service.connection.FoursquareConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * User: petrpopov
 * Date: 12.02.13
 * Time: 10:15
 */

@Controller
public class LoginController {

    @Autowired
    private FoursquareConnectionService foursquareConnectionService;

    @RequestMapping("/login")
    public String redirect(RedirectAttributes ra) {

        String url = foursquareConnectionService.getAuthorizeUrl();

        return "redirect: " + url;
        //return "login";
    }
}
