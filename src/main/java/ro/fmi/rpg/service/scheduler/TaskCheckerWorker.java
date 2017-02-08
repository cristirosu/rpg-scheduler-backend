package ro.fmi.rpg.service.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fmi.rpg.dao.entity.Character;
import ro.fmi.rpg.dao.entity.Task;
import ro.fmi.rpg.dao.entity.User;
import ro.fmi.rpg.dao.repository.CharacterRepository;
import ro.fmi.rpg.dao.repository.TaskRepository;
import ro.fmi.rpg.dao.repository.UserRepository;
import ro.fmi.rpg.service.email.EmailService;
import ro.fmi.rpg.service.email.EmailType;
import ro.fmi.rpg.service.notifications.NotificationService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by cristian.rosu on 2/8/2017.
 */
@Service
public class TaskCheckerWorker {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private NotificationService notificationService;

    private final static String LATE_TASK_NOTIFICATION = "Task not finished on time!";

    public void sendLateTaskEmailNotification(){
        List<Task> allTasks = taskRepository.findAllLefJoinFetch();
        allTasks = allTasks.stream()
                .filter(t -> !t.isFinished())
                .filter(t -> t.getDueDate().before(new Date()))
                .filter(t -> !t.isSentLateNotification()).collect(Collectors.toList());

        List<Task> toBeUpdated = new ArrayList<>();
        for(Task task : allTasks){
            User user = task.getCategory().getUser();
            if(user.isReceivesEmails() && !task.isSentLateNotification()){
                emailService.sendEmailWithContent(EmailType.LATE_TASK_EMAIL, task.getCategory().getUser(),
                        LATE_TASK_NOTIFICATION + "(" + task.getName() + ")");
                task.setSentLateNotification(true);
                toBeUpdated.add(task);
            }
            if(!task.isAccordedPenalty()){
                Character character = user.getCharacter();
                int health = character.getHealth();
                if(health >= 10) character.setHealth(health-10);
                task.setAccordedPenalty(true);
                characterRepository.save(character);
                characterRepository.flush();
                notificationService.sendNotification(user.getId(), "Late task(" + task.getName() +").Health penalty!:" + character.getHealth());
            }
        }

        taskRepository.save(toBeUpdated);
        taskRepository.flush();
    }

    public void sendBeforeTaskNotificaiton(){
        List<Task> allTasks = taskRepository.findAllLefJoinFetch();
        allTasks = allTasks.stream()
                .filter(t -> !t.isFinished())
                .filter(t -> t.getDueDate().after(new Date()) && (t.getDueDate().getTime() - new Date().getTime())/1000 < 3600
                        && (t.getDueDate().getTime() - new Date().getTime()) > 0)
                .filter(t -> !t.isSentBeforeDueDateNotificaiton()).collect(Collectors.toList());

        List<Task> toBeUpdated = new ArrayList<>();
        for(Task task : allTasks){
            User user = task.getCategory().getUser();
            if(user.isReceivesEmails() && !task.isSentBeforeDueDateNotificaiton()){
                emailService.sendEmailWithContent(EmailType.BEFORE_TASK_EMAIL, task.getCategory().getUser(),
                        "An unfinished task is due in less than an hour! (" + task.getName() +")");
                task.setSentBeforeDueDateNotificaiton(true);
                toBeUpdated.add(task);
            }
        }

        taskRepository.save(toBeUpdated);
    }

}
