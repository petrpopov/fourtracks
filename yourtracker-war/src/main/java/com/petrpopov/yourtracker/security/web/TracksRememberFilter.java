package com.petrpopov.yourtracker.security.web;

import com.petrpopov.yourtracker.security.usage.UsernameAuthenticationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: petrpopov
 * Date: 14.02.13
 * Time: 18:37
 */
@Component
public class TracksRememberFilter extends GenericFilterBean{

    @Autowired
    private TracksRememberMeServices rememberMeServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsernameAuthenticationTokenService usernameAuthenticationTokenService;

    public TracksRememberFilter() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        if (SecurityContextHolder.getContext().getAuthentication() != null)
        {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        Authentication rememberMeAuth = rememberMeServices.autoLoginWithCookie(request, response);
        if (rememberMeAuth == null)
        {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        try
        {
            if(rememberMeAuth instanceof RememberMeAuthenticationToken)
            {
                RememberMeAuthenticationToken t = (RememberMeAuthenticationToken) rememberMeAuth;
                UsernamePasswordAuthenticationToken auth = usernameAuthenticationTokenService.getUsernamePasswordToken(t);

                rememberMeAuth = authenticationManager.authenticate(auth);
                SecurityContextHolder.getContext().setAuthentication(rememberMeAuth);

                filterChain.doFilter(request, response);
            }
            else
                rememberMeServices.loginFail(request, response);
        }
        catch (AuthenticationException authenticationException) {
            rememberMeServices.loginFail(request, response);
        }
    }

}
