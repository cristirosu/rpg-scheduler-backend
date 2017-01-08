package ro.fmi.rpg.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Reciever {

    @JmsListener(destination = "mailbox")
    public void receiveMessage(String email) {
        System.out.println("Received <" + email + ">");
    }

}