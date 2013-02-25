package com.petrpopov.yourtracker.security.core;

import com.petrpopov.yourtracker.entity.UserEntity;
import com.petrpopov.yourtracker.security.usage.UserDetailsAssembler;
import com.petrpopov.yourtracker.service.mongo.UserStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * User: petrpopov
 * Date: 14.02.13
 * Time: 10:33
 */
@Component
public class TracksUserDetailsService implements UserDetailsService {

    @Autowired
    private UserStorageService userStorageService;

    @Autowired
    private UserDetailsAssembler userDetailsAssembler;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userStorageService.getUserByFoursquareId(username);

        if(user == null)
            throw new UsernameNotFoundException("User not found in MongoDB !");

        UserDetails u = userDetailsAssembler.fromUserToUserDetails(user);
        return u;
    }
}
