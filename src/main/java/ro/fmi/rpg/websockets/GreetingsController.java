package ro.fmi.rpg.websockets;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingsController {



    @MessageMapping("/hello/{p1}")
    @SendTo("/topic/{p1}")
    public String greeting(@DestinationVariable String p1, String message) throws Exception {
        Thread.sleep(1000); // simulated delay
        System.out.println("P1 ===== " + p1);
        System.out.println("SAID ==== " + message);
        return "Hello, " + message + "!";
    }

}