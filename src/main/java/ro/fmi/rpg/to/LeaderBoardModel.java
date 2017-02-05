package ro.fmi.rpg.to;

/**
 * Created by User on 2/5/2017.
 */
public class LeaderBoardModel {

    private String firstName;
    private String lastName;
    private String picture;
    private int level;
    private String email;

    public LeaderBoardModel() {
    }

    public LeaderBoardModel(String firstName, String lastName, String picture, int level, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.level = level;
        this.email = email;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
