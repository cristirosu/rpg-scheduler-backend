package ro.fmi.rpg.service.notifications;

/**
 * Created by User on 1/8/2017.
 */
public enum NotificationType {

    LEVEL_UP("Level UP!"),
    LEVEL_DOWN("Level DOWN!"),
    FIRST_TASK_CREATED("First Task Creation Achievement!"),
    FIRST_TASK_COMPLETED("First Task Completed Achievement!"),
    FRIEND_REQUEST("New friend request ");

    private String message;
    NotificationType(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
