package com.petrpopov.yourtracker.service.connection;

import com.petrpopov.yourtracker.config.AppSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.foursquare.api.Foursquare;
import org.springframework.social.foursquare.connect.FoursquareConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Component;

/**
 * User: petrpopov
 * Date: 15.02.13
 * Time: 11:56
 */

@Component
public class FoursquareConnectionService<T extends Foursquare> implements ConnectionService {

    @Autowired
    private FoursquareConnectionFactory factory;

    @Override
    public String getAuthorizeUrl()
    {
        OAuth2Operations oauthOperations = factory.getOAuthOperations();
        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri( AppSettings.CALLBACK_URL );

        String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, params);
        return url;
    }

    @Override
    public Connection<Foursquare> getConnection(String code)
    {
        OAuth2Operations oauthOperations = factory.getOAuthOperations();

        AccessGrant accessGrant = oauthOperations.exchangeForAccess(code, AppSettings.CALLBACK_URL, null);
        Connection<Foursquare> connection = factory.createConnection(accessGrant);

        return connection;
    }
}
