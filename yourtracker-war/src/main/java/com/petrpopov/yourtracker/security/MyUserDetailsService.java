package com.petrpopov.yourtracker.security;

import com.petrpopov.yourtracker.beans.UserService;
import com.petrpopov.yourtracker.model.User;
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
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAssembler userAssembler;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.getUserByFoursquareId(username);

        if(user == null)
            throw new UsernameNotFoundException("User not found in MongoDB !");

        UserDetails u = userAssembler.fromUserToUserDetails(user);
        return u;
    }
}
