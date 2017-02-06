package ro.fmi.rpg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.fmi.rpg.dao.entity.Character;
import ro.fmi.rpg.dao.entity.User;
import ro.fmi.rpg.dao.repository.UserRepository;
import ro.fmi.rpg.service.auth.SessionService;
import ro.fmi.rpg.to.user.CharacterModel;
import ro.fmi.rpg.to.user.UserModel;

/**
 * Created by User on 11/13/2016.
 */
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionService sessionService;

    public UserModel getLoggedInUser() {
        User user = userRepository.findOne(sessionService.getUserId());
        return getUserModelFromUser(user);
    }

    public UserModel getUserDetails(Integer userId) {
        User user = userRepository.findOne(userId);
        return getUserModelFromUser(user);
    }

    private UserModel getUserModelFromUser(User user){
        UserModel um = new UserModel();
        um.setEmail(user.getEmail());
        um.setFirstName(user.getFirstName());
        um.setLastName(user.getLastName());
        um.setId(user.getId());
        um.setPhone_number(um.getPhone_number());
        um.setBirthDate(user.getBirthDate());
        um.setPicture(user.getCharacter().getPicture());
        um.setReceivesToasts(user.isReceivesToasts());
        um.setReceivesEmails(user.isReceivesEmails());

        CharacterModel cm = new CharacterModel();
        Character character = user.getCharacter();

        cm.setId(character.getId());
        cm.setExperience(character.getExperience());
        cm.setHealth(character.getHealth());
        cm.setPicture(character.getPicture());
        cm.setLevel(character.getLevel());
        cm.setUserId(user.getId());

        um.setCharacter(cm);
        return um;
    }


}
