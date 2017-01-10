package ro.fmi.rpg.service.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ro.fmi.rpg.dao.entity.User;
import ro.fmi.rpg.dao.repository.FriendsRepository;

import java.util.List;

/**
 * Created by User on 1/8/2017.
 */
@Service
public class NotificationService {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private FriendsRepository friendsRepository;

    public void sendNotification(Object topic, String message){
        template.convertAndSend("/topic/" + topic, message);
    }

    public void notifyLogin(User sessionUser) {
        List<Integer> friendIds = friendsRepository.getFriendIdsByUser(sessionUser.getId());
        System.out.println("LOGIN NOTIFICATION on topic : " + friendIds);
        for(Integer id : friendIds){
            sendNotification(id, sessionUser.getFirstName() + " " + sessionUser.getLastName() + " just got online!");
        }
    }
}