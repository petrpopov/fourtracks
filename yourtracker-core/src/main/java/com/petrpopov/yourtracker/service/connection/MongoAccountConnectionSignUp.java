package com.petrpopov.yourtracker.service.connection;

import com.petrpopov.yourtracker.entity.UserEntity;
import com.petrpopov.yourtracker.security.usage.FoursquareConnectionFieldHandler;
import com.petrpopov.yourtracker.service.mongo.UserStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.stereotype.Component;

/**
 * User: petrpopov
 * Date: 15.02.13
 * Time: 12:57
 */

@Component
public class MongoAccountConnectionSignUp implements ConnectionSignUp {

    @Autowired
    private FoursquareConnectionFieldHandler foursquareConnectionFieldHandler;

    @Autowired
    private UserStorageService userStorageService;

    @Override
    public String execute(Connection<?> connection) {

        UserProfile profile = connection.fetchUserProfile();

        UserEntity user = new UserEntity();
        user.setFsId(connection.getKey().getProviderUserId());
        user.setFirstName(profile.getFirstName());
        user.setLastName( profile.getLastName() );
        user.setToken( foursquareConnectionFieldHandler.getAccessTokenFromConnection((OAuth2Connection) connection) );

        userStorageService.saveOrUpdate(user);

        return profile.getUsername();
    }
}
