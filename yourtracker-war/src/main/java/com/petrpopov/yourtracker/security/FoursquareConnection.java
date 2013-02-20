package com.petrpopov.yourtracker.security;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.oauth2.OAuth2ServiceProvider;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * User: petrpopov
 * Date: 15.02.13
 * Time: 22:17
 */
@Component
public class FoursquareConnection {

    public String getAccessToken(OAuth2Connection connection)
    {
        try
        {
            Field field = connection.getClass().getDeclaredField("accessToken");
            field.setAccessible(true);

            Object obj = field.get(connection);
            if(obj == null)
                return "";
            return obj.toString();
        }
        catch(Exception e)
        {
            return "";
        }
    }
}
