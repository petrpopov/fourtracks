package com.petrpopov.yourtracker.rest;

import com.petrpopov.yourtracker.config.TracksException;
import com.petrpopov.yourtracker.security.web.TracksRememberMeServices;
import com.petrpopov.yourtracker.service.mongo.CheckinStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.foursquare.api.Checkin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User: petrpopov
 * Date: 24.02.13
 * Time: 16:41
 */

@Controller
@RequestMapping("/api")
public class CheckinController {

    @Autowired
    private CheckinStorageService checkinStorageService;

    @Autowired
    private TracksRememberMeServices rememberMeServices;


    @RequestMapping(value="checkins/{userId}",  method = RequestMethod.GET)
    public @ResponseBody
    List<Checkin> getAllCheckins(@PathVariable String userId, HttpServletRequest request) throws TracksException {

        checkCookies(request);

        List<Checkin> list = checkinStorageService.getAllForUsername(userId);
        return list;
    }

    private void checkCookies(HttpServletRequest request) throws TracksException {

        Cookie[] cookies = request.getCookies();

        if( cookies == null )
            throw new TracksException("Access denied !");

        String cookieName = rememberMeServices.getCookieName();

        if( cookieName == null )
            throw new TracksException("Access denied !");

        if( cookieName.isEmpty() )
            throw new TracksException("Access denied !");

        boolean ok = false;
        for(Cookie c : cookies) {

            String name = c.getName();
            if( !name.equals(cookieName))
                continue;

            ok = true;
        }

        if( !ok )
            throw new TracksException("Access denied !");
    }
}
