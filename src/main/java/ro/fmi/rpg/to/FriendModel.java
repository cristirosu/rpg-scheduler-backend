package ro.fmi.rpg.to;

/**
 * Created by User on 1/8/2017.
 */
public class FriendModel {

    private int id;
    private String firstName;
    private String lastName;
    private int level;
    private int experience;
    private int health;
    private boolean isOnline;
    private String picture;

    public FriendModel(int id, String firstName, String lastName, int level, int experience, int health, boolean isOnline, String picture) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.level = level;
        this.experience = experience;
        this.health = health;
        this.isOnline = isOnline;
        this.picture = picture;
    }

    public FriendModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean online) {
        isOnline = online;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
