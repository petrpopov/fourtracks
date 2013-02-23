package com.petrpopov.yourtracker.service.mongo;

import com.petrpopov.yourtracker.entity.CheckinEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: petrpopov
 * Date: 21.02.13
 * Time: 13:14
 */

@Component
public class CheckinEntityService {

    @Autowired
    @Qualifier("mongoTemplate")
    private MongoOperations op;

    public void save(List<CheckinEntity> checkins) {
        op.insert(checkins, "checkins");
    }

    public List<CheckinEntity> findAll() {


        Query query = new Query();
        query.sort().on("createdAt", Order.DESCENDING);

        List<CheckinEntity> list = op.find(query, CheckinEntity.class, "checkins");
        return list;
    }
}
