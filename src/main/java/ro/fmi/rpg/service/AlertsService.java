package ro.fmi.rpg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fmi.rpg.dao.entity.UserEvent;
import ro.fmi.rpg.dao.repository.UserEventRepository;
import ro.fmi.rpg.service.auth.SessionService;
import ro.fmi.rpg.service.friends.FriendsService;
import ro.fmi.rpg.to.FriendModel;
import ro.fmi.rpg.to.UserEventModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by User on 2/7/2017.
 */
@Service
public class AlertsService {

    @Autowired
    private UserEventRepository userEventRepository;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private FriendsService friendsService;

    public List<UserEventModel> getUserAlerts(){
        List<Integer> friendIds = friendsService.getFriends().stream().map(FriendModel::getId).collect(Collectors.toList());
        List<UserEvent> userEvents = userEventRepository.getUserEvents(friendIds);
        return getEventModelFromEntity(userEvents);
    }

    public List<UserEventModel> getEventModelFromEntity(List<UserEvent> userEvents){
        List<UserEventModel> toReturn = new ArrayList<>();
        userEvents.forEach(e -> {
            UserEventModel event = new UserEventModel();
            event.setText(e.getDescription());
            event.setPicture(sessionService.getUser().getCharacter().getPicture());
            event.setName(sessionService.getUser().getFirstName());
            event.setTime(e.getDate());
            toReturn.add(event);
        });
        return toReturn;
    }

}
