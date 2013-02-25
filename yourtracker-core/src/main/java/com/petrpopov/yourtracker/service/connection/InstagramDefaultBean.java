package com.petrpopov.yourtracker.service.connection;

import org.springframework.social.instagram.api.*;

/**
 * User: petrpopov
 * Date: 25.02.13
 * Time: 17:43
 */
public class InstagramDefaultBean implements Instagram {
    @Override
    public TagOperations tagOperations() {
        return null;
    }

    @Override
    public LocationOperations locationOperations() {
        return null;
    }

    @Override
    public MediaOperations mediaOperations() {
        return null;
    }

    @Override
    public UserOperations userOperations() {
        return null;
    }
}
