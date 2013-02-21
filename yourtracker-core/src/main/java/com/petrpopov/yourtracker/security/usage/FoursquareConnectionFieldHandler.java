package com.petrpopov.yourtracker.security.usage;

import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * User: petrpopov
 * Date: 15.02.13
 * Time: 22:17
 */
@Component
public class FoursquareConnectionFieldHandler {

    public String getAccessTokenFromConnection(OAuth2Connection connection)
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
