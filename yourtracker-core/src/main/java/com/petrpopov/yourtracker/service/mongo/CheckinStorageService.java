package com.petrpopov.yourtracker.service.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.social.foursquare.api.Checkin;
import org.springframework.social.foursquare.api.CheckinInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: petrpopov
 * Date: 21.02.13
 * Time: 13:14
 */

@Component
public class CheckinStorageService {

    private final String COLLECTION_NAME = "checkins";

    @Autowired
    @Qualifier("mongoTemplate")
    private MongoOperations op;

    public void save(CheckinInfo info, String username) {
        CheckinInfo checkinInfo = this.insertUsername(info, username);

        for (Checkin checkin : checkinInfo.getItems()) {

            Checkin c = this.getById(checkin.getId());
            if( c == null )
                op.save(checkin, COLLECTION_NAME);
            else
                this.update(checkin);
        }
    }

    public Checkin getById(String id) {

        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);

        List<Checkin> checkins = op.find(query, Checkin.class, COLLECTION_NAME);
        if( checkins.isEmpty() )
            return null;

        return checkins.get(0);
    }

    public List<Checkin> getAllForUsername(String userId) {

        Criteria criteria = Criteria.where("username").is(userId);
        Query query = new Query(criteria);
        query.sort().on("createdAt", Order.DESCENDING);

        List<Checkin> list = op.find(query, Checkin.class, COLLECTION_NAME);
        return list;
    }

    private Checkin update(Checkin checkin) {
        Update update = new Update()
                .set("user", checkin.getUser())
                .set("createdAt", checkin.getCreatedAt())
                .set("type", checkin.getType())
                .set("isMayor", checkin.getisMayor())
                .set("timeZone", checkin.getTimeZone())
                .set("timeZoneOffset", checkin.getTimeZoneOffset())
                .set("venue", checkin.getVenue())
                .set("photos", checkin.getPhotos())
                .set("source", checkin.getSource())
                .set("comments", checkin.getComments())
                .set("like", checkin.isLike())
                .set("username", checkin.getUsername())
                .set("shout", checkin.getShout());

        Query query = new Query(Criteria.where("_id").is(checkin.getId()));
        Checkin c = op.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), Checkin.class);

        return c;
    }

    private CheckinInfo insertUsername(CheckinInfo info, String username) {
        for (Checkin checkin : info.getItems()) {
            checkin.setUsername(username);
        }
        return info;
    }
}
