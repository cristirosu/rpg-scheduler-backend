package ro.fmi.rpg.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import ro.fmi.rpg.exception.RPGException;

/**
 * Created by User on 1/8/2017.
 */
@RestController
public class TestSockCtrl {

    @Autowired
    private SimpMessagingTemplate template;

    @RequestMapping(path = "/test/{topic}", method = RequestMethod.POST)
    public void getLoggedInUser(@PathVariable("topic") String topic, @RequestBody String message) throws RPGException {
        template.convertAndSend("/topic/" + topic, message);
    }

}