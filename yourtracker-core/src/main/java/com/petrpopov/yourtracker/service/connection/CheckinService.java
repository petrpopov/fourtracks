package com.petrpopov.yourtracker.service.connection;

import com.petrpopov.yourtracker.entity.CheckinEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.foursquare.api.Checkin;
import org.springframework.social.foursquare.api.CheckinInfo;
import org.springframework.social.foursquare.api.Foursquare;
import org.springframework.social.foursquare.api.Venue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * User: petrpopov
 * Date: 21.02.13
 * Time: 20:41
 */

@Component
public class CheckinService {

    private int FETCH_SIZE = 250;

    @Autowired
    private Foursquare api;

    @Autowired
    private UsersConnectionRepository repository;

    public CheckinInfo getAllCheckins() {
        return getAllCheckinsByApi(api);
    }

    public CheckinInfo getAllCheckinsByUser(String userId) {
        ConnectionRepository connectionRepository = repository.createConnectionRepository(userId);
        Connection<Foursquare> connection = connectionRepository.getPrimaryConnection(Foursquare.class);
        Foursquare api1 = connection.getApi();

        return getAllCheckinsByApi(api1);
    }


    public List<CheckinEntity> getAllCheckinEntities() {

        CheckinInfo checkins = this.getAllCheckins();
        List<CheckinEntity> res = new ArrayList<CheckinEntity>();

        for(Checkin checkin : checkins.getItems()) {
            CheckinEntity entity = this.getCheckinEntityFromCheckin(checkin);

            if( entity != null)
                res.add(entity);
        }

        return res;
    }

    private CheckinEntity getCheckinEntityFromCheckin(Checkin checkin) {

        CheckinEntity entity = new CheckinEntity();

        Venue venue = checkin.getVenue();
        if( venue == null )
            return null;

        entity.setLocation(venue.getLocation());
        entity.setFoursquareId(checkin.getId());
        entity.setCreatedAt(checkin.getCreatedAt());
        entity.setTimeZone(checkin.getTimeZone());
        entity.setTimeZoneOffset(checkin.getTimeZoneOffset());

        return entity;
    }

    private CheckinInfo getCheckinsForOffset(CheckinInfo checkins, int offset) {
        CheckinInfo t = api.userOperations().getCheckins(FETCH_SIZE, offset);

        if(t == null)
            return checkins;

        checkins.setCount( checkins.getItems().size() + t.getItems().size() );
        checkins.getItems().addAll(t.getItems());

        return checkins;
    }

    private CheckinInfo getAllCheckinsByApi(Foursquare api) {

        CheckinInfo c = api.userOperations().getCheckins(1,0);
        int totalCount = c.getCount();

        CheckinInfo checkins = new CheckinInfo();

        int count = totalCount / FETCH_SIZE;
        for(int i = 0; i <= count; i++) {
            getCheckinsForOffset(checkins, i * FETCH_SIZE);
        }

        return checkins;
    }
}
