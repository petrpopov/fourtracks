package com.petrpopov.yourtracker.beans;

import com.petrpopov.yourtracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

/**
 * User: petrpopov
 * Date: 12.02.13
 * Time: 13:33
 */
@Component("userService")
public class UserService {

    @Autowired
    @Qualifier("mongoTemplate")
    private MongoOperations op;


    public User getUserByFoursquareId(String fsId)
    {
        Criteria criteria = Criteria.where("fsId").is(fsId);
        Query query = new Query(criteria);

        return op.findOne(query, User.class);
    }

    public User getUserByCookieId(String cookie)
    {
        Criteria criteria = Criteria.where("cookieId").is(cookie);
        Query query = new Query(criteria);

        return op.findOne(query, User.class);
    }

    public User saveOrUpdate(User user)
    {
        User u = this.getUserByFoursquareId(user.getFsId());
        if( u == null )
            op.save(user);
        else
        {
            Update update = new Update()
                    .set("firstName", user.getFirstName())
                    .set("lastName", user.getLastName())
                    .set("token", user.getToken() );

            Query query = new Query(Criteria.where("fsId").is(user.getFsId()));
            u = op.findAndModify(query, update,
                    new FindAndModifyOptions().returnNew(true), User.class);
        }

        return u;
    }
}
