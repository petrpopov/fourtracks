package com.petrpopov.yourtracker.service.logic;

import com.petrpopov.yourtracker.entity.UserEntity;
import com.petrpopov.yourtracker.service.connection.CheckinService;
import com.petrpopov.yourtracker.service.mongo.UserStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.social.foursquare.api.CheckinInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: petrpopov
 * Date: 25.02.13
 * Time: 21:28
 */

@Component
public class UserUpdateService {

    @Autowired
    private UserStorageService userStorageService;

    @Autowired
    private CheckinService checkinService;

    @Scheduled(fixedDelay=5000)
    public void updateUsersInformation() {

        List<UserEntity> list = userStorageService.findAll();
        for (UserEntity entity : list) {
            this.updateUserInformation(entity);
        }
    }

    @Async
    public void updateUserInformation(UserEntity entity) {

        String id = entity.getFsId();
        CheckinInfo info = checkinService.getAllCheckinsByUser(id);
    }
}
