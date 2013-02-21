package com.petrpopov.yourtracker.config;

import com.petrpopov.yourtracker.service.connection.FoursquareDefaultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.*;
import org.springframework.social.connect.mongo.MongoConnectionService;
import org.springframework.social.connect.mongo.MongoUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.foursquare.api.Foursquare;
import org.springframework.social.foursquare.connect.FoursquareConnectionFactory;

/**
 * User: petrpopov
 * Date: 15.02.13
 * Time: 11:11
 */

@Configuration
public class SocialConfig {

    @Autowired
    private TextEncryptor encryptor;

    @Autowired
    private MongoConnectionService mongoConnectionService;

    @Autowired
    private ConnectionSignUp connectionSignUp;

    @Bean
    public ConnectionFactoryLocator connectionFactoryLocator()
    {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();

        FoursquareConnectionFactory factory = new FoursquareConnectionFactory(AppSettings.CLIENT_ID, AppSettings.CLIENT_SECRET);
        registry.addConnectionFactory(factory);

        return registry;
    }

    @Bean
    public FoursquareConnectionFactory foursquareConnectionFactory()
    {
        FoursquareConnectionFactory factory = (FoursquareConnectionFactory) connectionFactoryLocator().getConnectionFactory(Foursquare.class);
        return factory;
    }

    @Bean
    public UsersConnectionRepository usersConnectionRepository()
    {
        MongoUsersConnectionRepository repo = new MongoUsersConnectionRepository(mongoConnectionService, connectionFactoryLocator(), encryptor);
        repo.setConnectionSignUp( connectionSignUp );

        return repo;
    }

    @Bean
    @Scope(value="request", proxyMode= ScopedProxyMode.INTERFACES)
    public ConnectionRepository connectionRepository()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed");
        }

        String name = authentication.getName();
        ConnectionRepository repo = usersConnectionRepository().createConnectionRepository(name);
        return repo;
    }

    @Bean
    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
    public Foursquare foursquare()
    {
        ConnectionRepository repo = connectionRepository();
        try {
            Connection<Foursquare> connection = repo.getPrimaryConnection(Foursquare.class);
            Foursquare api = connection.getApi();
            return api;
        }
        catch (Exception e) {
            return new FoursquareDefaultBean();
        }
    }

}
