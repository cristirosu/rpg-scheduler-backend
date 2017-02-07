package ro.fmi.rpg.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.fmi.rpg.service.AlertsService;
import ro.fmi.rpg.to.UserEventModel;

import java.util.List;

/**
 * Created by User on 2/7/2017.
 */
@RestController
@RequestMapping("/api")
public class AlertsController {

    @Autowired
    private AlertsService alertsService;

    @RequestMapping(path = "/alerts/notifications", method = RequestMethod.GET)
    public List<UserEventModel> getCategories(){
        return alertsService.getUserAlerts();
    }

}