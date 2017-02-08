package ro.fmi.rpg.service.email;

/**
 * Created by User on 11/4/2016.
 */
public enum EmailType {

    ACTIVATION_EMAIL("Activation notification"),
    LATE_TASK_EMAIL("Late task notification"),
    BEFORE_TASK_EMAIL("Late task notification");

    private String title;

    private EmailType(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

}
