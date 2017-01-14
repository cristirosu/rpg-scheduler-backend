package ro.fmi.rpg.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.fmi.rpg.exception.RPGException;
import ro.fmi.rpg.service.UserService;
import ro.fmi.rpg.to.user.UserModel;

/**
 * Created by User on 12/18/2016.
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public UserModel getLoggedInUser() throws RPGException {
        return userService.getLoggedInUser();
    }

    @RequestMapping(path = "/users/{userId}", method = RequestMethod.GET)
    public UserModel getUserDetails(@PathVariable("userId") Integer userId) throws RPGException {
        return userService.getUserDetails(userId);
    }

}
