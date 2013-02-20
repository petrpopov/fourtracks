package com.petrpopov.yourtracker.web;

import com.petrpopov.yourtracker.security.FoursquareService;
import com.petrpopov.yourtracker.security.LoginManager;
import com.petrpopov.yourtracker.security.MyRememberMeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.foursquare.api.Foursquare;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: petrpopov
 * Date: 12.02.13
 * Time: 11:03
 */

@Controller
@RequestMapping("/connect111/foursquare")
public class CallbackController {

    @Autowired
    private FoursquareService foursquareService;

    @Autowired
    private LoginManager loginManager;

    @Autowired
    private MyRememberMeServices rememberMeServices;

    @RequestMapping(method = RequestMethod.GET)
    public String empty()
    {
        return "home";
    }

    @RequestMapping(method = RequestMethod.GET, params = "code")
    public ModelAndView foursquareCallback(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response, RedirectAttributes ra)
    {
       // String token = manager.getOAuthToken(code);

        //CompleteUser user = manager.getUser(token);

       // Authentication auth = loginManager.authenticate( user.getId(), token );

        //rememberMeServices.onLoginSuccess(request, response, auth);

        Connection<Foursquare> con = foursquareService.getConnection(code);


        return new ModelAndView("redirect:/");
    }
}
