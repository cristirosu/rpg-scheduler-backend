package ro.fmi.rpg.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.fmi.rpg.exception.RPGException;
import ro.fmi.rpg.service.LeaderBoardService;
import ro.fmi.rpg.service.friends.FriendsService;
import ro.fmi.rpg.to.FriendModel;
import ro.fmi.rpg.to.LeaderBoardModel;

import java.util.List;

/**
 * Created by User on 1/8/2017.
 */
@RestController
@RequestMapping("/api")
public class FriendsController {

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private LeaderBoardService leaderBoardService;

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

    @RequestMapping(path = "/friends/scoreBoard", method = RequestMethod.GET)
    public List<LeaderBoardModel> getScoreBoard() throws RPGException {
        return leaderBoardService.getLeaderBoard();
    }

}
