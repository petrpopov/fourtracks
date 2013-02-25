package com.petrpopov.yourtracker.web;

import com.petrpopov.yourtracker.service.connection.ConnectionService;
import com.petrpopov.yourtracker.service.connection.FoursquareConnectionService;
import com.petrpopov.yourtracker.service.connection.InstagramConnectionService;
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

    @Autowired
    private InstagramConnectionService instagramConnectionService;

    @RequestMapping("/login")
    public String redirect(RedirectAttributes ra) {
        return getRedirectUrl(foursquareConnectionService);
    }

    @RequestMapping("/login/instagram")
    public String instagramLogin(RedirectAttributes ra) {
        return getRedirectUrl(instagramConnectionService);
    }

    private String getRedirectUrl(ConnectionService connectionService) {
        String url = connectionService.getAuthorizeUrl();
        return "redirect: " + url;
    }
}
