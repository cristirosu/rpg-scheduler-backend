package ro.fmi.rpg.to.task;

/**
 * Created by User on 12/20/2016.
 */
public class AchievementModel {

    private String name;
    private String description;
    private String picture;

    public AchievementModel(String name, String description, String picture) {
        this.name = name;
        this.description = description;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
