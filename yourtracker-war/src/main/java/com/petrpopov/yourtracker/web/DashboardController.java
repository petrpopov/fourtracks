package com.petrpopov.yourtracker.web;

import com.google.appengine.repackaged.org.json.JSONArray;
import com.google.appengine.repackaged.org.json.JSONObject;
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
       // List<CheckinEntity> list = checkinService.getAllCheckinEntities();

       // checkinEntityService.save(list);

        List<CheckinEntity> list = checkinEntityService.findAll();

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        try
        {
            for(int i = 0; i < 4; i++) {

                JSONObject t = new JSONObject();
                t.put("id", list.get(i).getFoursquareId());
                t.put("createdAt", list.get(i).getCreatedAt());
                t.put("timeZone", list.get(i).getTimeZone());
                t.put("timeZoneOffset", list.get(i).getTimeZoneOffset());
                t.put("latitude", list.get(i).getLocation().getLatitude());
                t.put("longitude", list.get(i).getLocation().getLongitude());

                jsonArray.put(t);
            }

            jsonObject.put("checkins", jsonArray);
        }
        catch (Exception e){}

        model.put("checkins", jsonObject.toString());



        return new ModelAndView("dashboard", model);
    }
}
