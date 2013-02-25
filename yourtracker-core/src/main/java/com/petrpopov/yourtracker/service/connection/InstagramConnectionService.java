package com.petrpopov.yourtracker.service.connection;

import com.petrpopov.yourtracker.config.AppSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.instagram.api.Instagram;
import org.springframework.social.instagram.connect.InstagramConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Component;

/**
 * User: petrpopov
 * Date: 25.02.13
 * Time: 17:45
 */

@Component
public class InstagramConnectionService<T extends Instagram> implements ConnectionService {

    @Autowired
    private InstagramConnectionFactory factory;

    @Override
    public String getAuthorizeUrl() {

        OAuth2Operations oauthOperations = factory.getOAuthOperations();
        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri( AppSettings.INSTAGRAM_CALLBACK_URL );

        String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, params);
        return url;
    }

    @Override
    public Connection<Instagram> getConnection(String code) {
        OAuth2Operations oauthOperations = factory.getOAuthOperations();

        AccessGrant accessGrant = oauthOperations.exchangeForAccess(code, AppSettings.INSTAGRAM_CALLBACK_URL, null);
        Connection<Instagram> connection = factory.createConnection(accessGrant);

        return connection;
    }
}
