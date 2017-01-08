package ro.fmi.rpg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fmi.rpg.dao.entity.Achievement;
import ro.fmi.rpg.dao.entity.Character;
import ro.fmi.rpg.dao.entity.Task;
import ro.fmi.rpg.dao.repository.AcvhievementRepository;
import ro.fmi.rpg.dao.repository.CharacterRepository;
import ro.fmi.rpg.dao.repository.TaskRepository;
import ro.fmi.rpg.dao.repository.UserRepository;
import ro.fmi.rpg.service.auth.SessionService;
import ro.fmi.rpg.service.notifications.NotificationService;
import ro.fmi.rpg.service.notifications.NotificationType;
import ro.fmi.rpg.to.task.AchievementModel;

import java.util.List;

/**
 * Created by User on 12/20/2016.
 */
@Service
public class RewardService {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private AcvhievementRepository acvhievementRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    private final static int XP_REWARD = 50;

    public void rewardXp(Task task) {
        Character character = sessionService.getUser().getCharacter();
        int rewardXp = XP_REWARD - ((character.getLevel() - 1) * 5);
        if(task.getExperience() != null){
            rewardXp = task.getExperience();
        }
        if (task.isFinished()) {
            character.setExperience(character.getExperience() + rewardXp);
            if (character.getExperience() >= 100) {
                int nextLvlXp = character.getExperience() - 100;
                character.setExperience(nextLvlXp);
                character.setLevel(character.getLevel() + 1);
                notificationService.sendNotification(sessionService.getUserId(), NotificationType.LEVEL_UP.getMessage());
            }
            if(task.getExperience() == null){
                task.setExperience(rewardXp);
            }
        } else {
            int calculatedXp = character.getExperience() - task.getExperience();
            if (calculatedXp < 0) {
                character.setExperience(100 + calculatedXp);
                character.setLevel(character.getLevel() - 1);
                notificationService.sendNotification(sessionService.getUserId(), NotificationType.LEVEL_DOWN.getMessage());
            } else{
                character.setExperience(calculatedXp);
            }
        }
        taskRepository.save(task);
        characterRepository.save(character);
    }

    public void rewardFirstTaskBadge(){
        Achievement acvhievement = acvhievementRepository.getAcvhievementByName("FIRST_TASK", sessionService.getUser().getId());

        if(acvhievement != null){
            return;
        }

        Achievement achievement = new Achievement();
        achievement.setDescription("Create your first task!");
        achievement.setName("FIRST_TASK");
        achievement.setPicture("http://information.edmition.com/wp-content/uploads/2016/06/achievement-bang2.jpg");
        achievement.setUser(userRepository.findOne(sessionService.getUser().getId()));

        acvhievementRepository.save(achievement);
        notificationService.sendNotification(sessionService.getUserId(), "First Task Creation!");
    }

    public void rewardFirstTaskCompleted(){
        Achievement acvhievement = acvhievementRepository.getAcvhievementByName("FIRST_TASK_COMPLETED", sessionService.getUser().getId());

        if(acvhievement != null){
            return;
        }

        Achievement achievement = new Achievement();
        achievement.setDescription("Complete your first task!");
        achievement.setName("FIRST_TASK_COMPLETED");
        achievement.setPicture("https://hannahsarahlewis.files.wordpress.com/2011/02/madmaxer090400293.jpg");
        achievement.setUser(userRepository.findOne(sessionService.getUser().getId()));

        acvhievementRepository.save(achievement);
        notificationService.sendNotification(sessionService.getUserId(), "First Task Completed!");
    }

    public List<AchievementModel> getAchievements(){
        List<AchievementModel> achievements = acvhievementRepository.getAchievementsForUser(sessionService.getUser().getId());
        return achievements;
    }

}
