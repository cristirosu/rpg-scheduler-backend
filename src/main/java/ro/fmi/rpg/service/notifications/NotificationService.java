package ro.fmi.rpg.service.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by User on 1/8/2017.
 */
@Service
public class NotificationService {

    @Autowired
    private SimpMessagingTemplate template;

    public void sendNotification(Object topic, String message){
        template.convertAndSend("/topic/" + topic, message);
    }

}