package ro.fmi.rpg.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.fmi.rpg.service.CategoryService;
import ro.fmi.rpg.service.RewardService;
import ro.fmi.rpg.to.task.AchievementModel;

import java.util.List;

/**
 * Created by User on 12/20/2016.
 */

@RestController
@CrossOrigin
public class AchievementController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RewardService rewardService;

    @CrossOrigin
    @RequestMapping(path = "/achievements", method = RequestMethod.GET)
    public List<AchievementModel> getCategories(){
        return rewardService.getAchievements();
    }

}