package ro.fmi.rpg.service.friends;

import org.postgresql.core.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fmi.rpg.dao.entity.User;
import ro.fmi.rpg.dao.entity.UserFriend;
import ro.fmi.rpg.dao.repository.FriendsRepository;
import ro.fmi.rpg.dao.repository.UserRepository;
import ro.fmi.rpg.exception.RPGException;
import ro.fmi.rpg.service.UserService;
import ro.fmi.rpg.service.auth.SessionService;
import ro.fmi.rpg.service.notifications.NotificationService;
import ro.fmi.rpg.service.notifications.NotificationType;
import ro.fmi.rpg.to.FriendModel;

import java.util.List;

/**
 * Created by User on 1/8/2017.
 */
@Service
public class FriendsService {

    @Autowired
    private FriendsRepository friendsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SessionService sessionService;

    public List<FriendModel> getFriends(){
        System.out.println(userService.getLoggedInUser().getId());
        return friendsRepository.getFriendsByUser(userService.getLoggedInUser().getId());
    }

    public void deleteFriend(Integer friendId) {
        UserFriend uf = friendsRepository.findByFriendId(userService.getLoggedInUser().getId(), friendId);
        if(uf != null){
            friendsRepository.delete(uf);
        }
    }

    public void addFriend(String emailAddress) throws RPGException {
        List<FriendModel> friendShip = friendsRepository.getFriendsByUser(sessionService.getUserId());
        if(!friendShip.isEmpty()){
            throw new RPGException("You are already friends with this person");
        }
        User friend = userRepository.findUserByEmail(emailAddress);
        if(friend == null){
            throw new RPGException("No users registered with provided address");
        }
        UserFriend uf = new UserFriend();
        User u = userRepository.findOne(userService.getLoggedInUser().getId());
        uf.setFriend(friend);
        uf.setUser(u);

        friendsRepository.save(uf);

        notificationService.sendNotification(friend.getId(), NotificationType.FRIEND_REQUEST.getMessage() + "from " + u.getFirstName() + " " + u.getLastName());
    }
}
