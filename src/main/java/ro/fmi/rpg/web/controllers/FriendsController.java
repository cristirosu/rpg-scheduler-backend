package ro.fmi.rpg.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.fmi.rpg.exception.RPGException;
import ro.fmi.rpg.service.friends.FriendsService;
import ro.fmi.rpg.to.FriendModel;

import java.util.List;

/**
 * Created by User on 1/8/2017.
 */
@RestController
public class FriendsController {

    @Autowired
    private FriendsService friendsService;

    @RequestMapping(path = "/friends", method = RequestMethod.GET)
    public List<FriendModel> getFriends() throws RPGException {
        return friendsService.getFriends();
    }

    @RequestMapping(path = "/friends/{friendId}", method = RequestMethod.DELETE)
    public void deleteFriend(@PathVariable("friendId") Integer friendId) throws RPGException {
        friendsService.deleteFriend(friendId);
    }

    @RequestMapping(path = "/friends/request/{emailAddress:.+}", method = RequestMethod.POST)
    public void addFriend(@PathVariable("emailAddress") String emailAddress) throws RPGException {
        friendsService.addFriend(emailAddress);
    }

}
