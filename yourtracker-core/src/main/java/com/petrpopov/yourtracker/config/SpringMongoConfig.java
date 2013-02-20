package com.petrpopov.yourtracker.config;

import com.mongodb.Mongo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;



/**
 * User: petrpopov
 * Date: 12.02.13
 * Time: 12:57
 */

@Configuration
public class SpringMongoConfig {

    public @Bean
    MongoDbFactory mongoDbFactory() throws Exception {
        log().info("Creating MongoDB factory");

        Mongo mongo = new Mongo( AppSettings.MONGODB_HOST );
        return new SimpleMongoDbFactory(mongo, AppSettings.MONGODB_DB );
    }

    public @Bean
    MongoTemplate mongoTemplate() throws Exception {
        log().info("Creating MongoTemplate");

        return new MongoTemplate(mongoDbFactory());
    }

    private Logger log()
    {
        return Logger.getLogger(SpringMongoConfig.class);
    }

    @Autowired
    private AppSettings appSettings;
}
