package com.petrpopov.yourtracker.web;

import com.petrpopov.yourtracker.entity.CheckinEntity;
import com.petrpopov.yourtracker.service.connection.CheckinService;
import com.petrpopov.yourtracker.service.mongo.CheckinEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * User: petrpopov
 * Date: 17.02.13
 * Time: 19:38
 */
@Controller
public class DashboardController {

    @Autowired
    private CheckinService checkinService;

    @Autowired
    private CheckinEntityService checkinEntityService;

    @RequestMapping("/dashboard")
    public ModelAndView dashboard(Map<String, Object> model)
    {
        List<CheckinEntity> list = checkinService.getAllCheckinEntities();

        checkinEntityService.save(list);

        return new ModelAndView("dashboard", model);
    }
}
