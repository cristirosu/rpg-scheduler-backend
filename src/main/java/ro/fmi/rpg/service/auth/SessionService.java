package ro.fmi.rpg.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ro.fmi.rpg.dao.entity.Character;
import ro.fmi.rpg.dao.entity.User;
import ro.fmi.rpg.dao.repository.CharacterRepository;
import ro.fmi.rpg.dao.repository.UserRepository;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by User on 11/13/2016.
 */
@Component
@Scope(value= WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionService {

    private User user;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Value("${dev}")
    private String dev;

    public SessionService() {

    }

    public SessionService(User user) {
        this.user = user;
    }

    public User getUser() {
        if(userRepository.exists(1) && dev.equals("true")){
            this.user = userRepository.findOne(1);
            System.out.println("Heree");
            return this.user;
        }
        if(user == null && dev.equals("true")){
            User user = new User();
            user.setBirthDate(new Date());
            user.setConfirmed(true);
            user.setEmail("cristi.rosu4@gmail.com");
            user.setFirstName("FakeUser");
            user.setLastName("FakeUser");
            user.setPassword("123456");
            user.setPhoneNumber("0767895301");

            userRepository.save(user);
            //Character character = characterRepository.getChar(user.getId());

            Character char1 = new Character();
            char1.setLevel(1);
            char1.setPicture("");
            char1.setHealth(100);
            char1.setExperience(20);
            char1.setUser(user);
            user.setCharacter(char1);

            characterRepository.save(char1);

            User u = userRepository.save(user);
            this.user = u;
        }
        return user;
    }

    public int getUserId(){
        System.out.println("Getting ID ==> " + user.getId());
        return this.user.getId();
    }

    public void setUser(User user) {
        System.out.println("Setting session user ===> " + user.getId() + "  :  " + user.getEmail());
        this.user = user;
    }

    @Override
    public String toString() {
        return "SessionService{" +
                "user=" + user +
                '}';
    }

}
