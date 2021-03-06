package com.petrpopov.yourtracker.web;

import com.petrpopov.yourtracker.security.web.LoginManager;
import com.petrpopov.yourtracker.security.web.TracksRememberMeServices;
import com.petrpopov.yourtracker.service.connection.FoursquareConnectionService;
import com.petrpopov.yourtracker.service.connection.InstagramConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.foursquare.api.Foursquare;
import org.springframework.social.instagram.api.Instagram;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * User: petrpopov
 * Date: 15.02.13
 * Time: 20:20
 */
@Controller
public class SocialController  {

    @Autowired
    private FoursquareConnectionService foursquareConnectionService;

    @Autowired
    private InstagramConnectionService instagramConnectionService;

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private ConnectionSignUp connectionSignUp;

    @Autowired
    private TracksRememberMeServices rememberMeServices;

    @Autowired
    private LoginManager loginManager;


    @RequestMapping(value="/connect/foursquare", method= RequestMethod.POST)
    public RedirectView foursquareConnect() {

        String url = foursquareConnectionService.getAuthorizeUrl();

        return new RedirectView(url);
    }

    @RequestMapping(value="/connect/foursquare", method=RequestMethod.GET, params="code")
    public RedirectView foursquareCallback(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response)
    {
        OAuth2Connection<Foursquare> connection = (OAuth2Connection<Foursquare>) foursquareConnectionService.getConnection(code);

        connectionSignUp.execute(connection);
        Authentication authentication = loginManager.authenticate(connection);


        try {
            List<Connection<Foursquare>> list = connectionRepository.findConnections(Foursquare.class);
            if( list.size() > 0 )
                connectionRepository.removeConnections("foursquare");
        }
        catch (Exception e)
        {
        }

        connectionRepository.addConnection(connection);


        rememberMeServices.onLoginSuccess(request, response, authentication);


        return new RedirectView("/dashboard", true);
    }

    @RequestMapping(value="/logout", method=RequestMethod.DELETE)
    public RedirectView foursquareLogout(HttpServletRequest request, HttpServletResponse response) {

        connectionRepository.removeConnections("foursquare");

        loginManager.logout(request, response);

        return new RedirectView("/index", true);
    }


    @RequestMapping(value="/connect/instagram", method= RequestMethod.POST)
    public RedirectView instagramConnect() {

        String url = instagramConnectionService.getAuthorizeUrl();

        return new RedirectView(url);
    }

    @RequestMapping(value="/connect/instagram", method=RequestMethod.GET, params="code")
    public RedirectView instagramCallback(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) {

        OAuth2Connection<Instagram> connection = (OAuth2Connection<Instagram>) instagramConnectionService.getConnection(code);
        connectionRepository.addConnection(connection);

        return new RedirectView("/dashboard", true);
    }
}
