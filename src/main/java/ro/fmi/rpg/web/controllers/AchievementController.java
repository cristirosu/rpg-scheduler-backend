package ro.fmi.rpg.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.fmi.rpg.service.CategoryService;
import ro.fmi.rpg.service.RewardService;
import ro.fmi.rpg.to.task.AchievementModel;

import java.util.List;

/**
 * Created by User on 12/20/2016.
 */

@RestController
@RequestMapping("/api")
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

    @CrossOrigin
    @RequestMapping(path = "/achievements/{userId}", method = RequestMethod.GET)
    public List<AchievementModel> getCategories(@PathVariable("userId") Integer userId){
        return rewardService.getAchievementsByUserId(userId);
    }

}