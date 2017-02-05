package ro.fmi.rpg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fmi.rpg.dao.entity.User;
import ro.fmi.rpg.dao.repository.CharacterRepository;
import ro.fmi.rpg.dao.repository.FriendsRepository;
import ro.fmi.rpg.dao.repository.UserRepository;
import ro.fmi.rpg.service.auth.SessionService;
import ro.fmi.rpg.to.LeaderBoardModel;

import java.util.Comparator;
import java.util.List;

/**
 * Created by User on 2/5/2017.
 */
@Service
public class LeaderBoardService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendsRepository friendsRepository;

    @Autowired
    private SessionService sessionService;

    public List<LeaderBoardModel> getLeaderBoard(){
        List<LeaderBoardModel> toReturn = friendsRepository.getScoreBoard(sessionService.getUser().getId());
        User currentUser = sessionService.getUser();
        toReturn.add(new LeaderBoardModel(currentUser.getFirstName(),
                currentUser.getLastName(), currentUser.getCharacter()
                .getPicture(), currentUser.getCharacter().getLevel(), currentUser.getEmail()));
        toReturn.sort(Comparator.comparing(LeaderBoardModel::getLevel).reversed());

        return toReturn;
    }

}
